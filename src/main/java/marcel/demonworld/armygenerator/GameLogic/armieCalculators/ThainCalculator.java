package marcel.demonworld.armygenerator.GameLogic.armieCalculators;

import marcel.demonworld.armygenerator.GameLogic.ResultContainers.ThainResultContainer;
import marcel.demonworld.armygenerator.dto.armyDTOs.CalculatedArmyResult;

import static marcel.demonworld.armygenerator.GameLogic.constants.SubFactions.thain.ThainSubFaction.*;

import marcel.demonworld.armygenerator.dto.statCardDTOs.DemonWorldCard;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * additional rule
 * - EVERY UNIT, ARTILLERY PIECE, CHAMPION,... WITH THE EXCEPTION OF DORGAPRIESTS, CHURCH UNITS AND GARYDWEN MUST BE ASSIGNED TO A TRIBE
 *  - tribal champions can only be selected when at least ONE unit of the tribe has been selected
 *  - for every veteran unit of a tribe you need ONE tribal unit
 *  - Dorga priests can only be selected if at least ONE church unit has been selected
 *  - in addition to the maximum for shamans and heroes/champions the points for champions/heroes AND shamans must be <= 50
 *  - for every FULL 10% of shamans, the maximum for church/droga units is lowered by 10%
 *    * e.g. 25% shamans -> -20% for church units
 *
 * */
public class ThainCalculator implements ArmyCalculator {

    @Autowired
    ThainResultContainer container;


    /*
     * data structure for number of  Veteran Units the player can add
     * */
    private final String[] TRIBES = {"Adler", "Wolf", "Bär", "Silberlöwe", "Eber"};

    private final static String[] GREAT_CHAMPION_NAMES = {
            "Arr'ydwen der wilde Eber",
            "Bold'dyrr der einäugige Bär",
            "Shariga'kyan der leise Tod", "Har'anyrrd der Späher",
            "Dargorkon'yaghar d. Winterwolf"};


    private Map<String, Integer> numberOfVeteranUnitsPerTribe = new HashMap<String, Integer>() {{
        put(TRIBES[0], 0);
        put(TRIBES[1], 0);
        put(TRIBES[2], 0);
        put(TRIBES[3], 0);
        put(TRIBES[4], 0);
    }};


    @Override
    public CalculatedArmyResult CalculatePointCost(List<DemonWorldCard> list, float maximumPointValue) {


        for (DemonWorldCard uc : list) {

            switch (uc.getSubFaction()) {
                case SCHAMANEN:
                    container.setSchamanenSum(container.getSchamanenSum() + uc.getPoints());

                    if (container.getSchamanenSum() <= maximumPointValue * .50) {
                        container.setFlagSchamanen(true);
                    }
                    break;
                case KIRCHE:
                    container.setKircheSum(container.getKircheSum() + uc.getPoints());

                    if (container.getKircheSum() <= maximumPointValue * .40) {
                        container.setFlagKirche(true);
                    }
                    break;
                case VETERANEN:
                    container.setVeteranenSum(container.getVeteranenSum() + uc.getPoints());

                    if (container.getVeteranenSum() <= maximumPointValue * .50 && confirmVeteranCountIsValid(list, TRIBES)) {
                        container.setFlagVeteranen(true);
                    }
                    break;
                case STAMMESKRIEGER:
                    container.setStammeskriegerSum(container.getStammeskriegerSum() + uc.getPoints());

                    if (container.getStammeskriegerSum() >= maximumPointValue * .20 && container.getStammeskriegerSum() <= maximumPointValue * .80) {
                        container.setFlagStammeskrieger(true);
                    }
                    break;
                case CHAMPIONS_HELDEN:
                    container.setChampions_heldenSum(container.getChampions_heldenSum() + uc.getPoints());

                    if (container.getChampions_heldenSum() <= maximumPointValue * .30 && checkIfGreatChampionsAllowed(list)) {
                        container.setFlagChampions_helden(true);
                    } else if (container.getChampions_heldenSum() > maximumPointValue * .30) {

                        //TODO: SEND ERROR MESSAGE TO FRONTED

                    } else if (!checkIfGreatChampionsAllowed(list)) {

                        //TODO: SEND ERROR MESSAGE TO FRONTED
                    }

                    break;
                case GARYDWEN:
                    container.setGarydwenSum(container.getGarydwenSum() + uc.getPoints());

                    if (container.getGarydwenSum() <= maximumPointValue * .40) {
                        container.setFlagGarydwen(true);
                    }
                    break;
            }

        }

        return null;
    }


    /**
     * Assert that number of veteran units per tribe does not exceed the maximum.
     *
     * @param armyList The Thain ArmyList.
     * @param tribes   The list of tribes.
     * @return boolean flag
     */
    private boolean confirmVeteranCountIsValid(List<DemonWorldCard> armyList, String[] tribes) {

        boolean result = true;

        // set correct max number of veterans per tribe
        for (String tribe : tribes) {
            maxNumberOfVeteransOfTribe(armyList, tribe);
        }

        //assert number <= max number
        for (String tribe : tribes) {
            if (!(numberOfVeteranUnitsPerTribe.get(tribe) == currentNumberOfVeterans(armyList, tribe)))
                result = false;
            break;
        }
        return result;
    }

    /**
     * Determine max number of veteran for one tribe.
     *
     * @param armyList The Thain ArmyList.
     * @param tribe    one Thain tribe
     */
    private void maxNumberOfVeteransOfTribe(List<DemonWorldCard> armyList, String tribe) {
        numberOfVeteranUnitsPerTribe.put(tribe, (int) armyList.
                stream().
                filter(c -> c instanceof UnitCard && c.getSubFaction().contains(tribe)
                        && c.getSubFaction().contains("Stammeskrieger")).count());

    }

    /**
     * Actual number of veteran units.
     *
     * @param armyList The Thain ArmyList.
     * @param tribe    one Thain tribe
     * @return number of units.
     */
    private long currentNumberOfVeterans(List<DemonWorldCard> armyList, String tribe) {
        return armyList.stream().
                filter(c -> c instanceof UnitCard && c.getSubFaction().equalsIgnoreCase(tribe)).count();
    }


    /**
     * ALL units that are not Dorgar, Garydwen must be assigned to a tribe at the moment of selection.
     * TODO: the assignment of non-Drogar, non-Garydwen Thain units must be done in the frontend code by calling this
     * TODO[continuation] method. Try to detach it from the frontend logic as much as possible.
     *
     * @param unit  the unit that must be assigned to a tribe.
     * @param tribe the tribe it is assigned to.
     */
    public void assignTribeToUnit(UnitCard unit, String tribe) {
        unit.setSubFaction(unit.getSubFaction().concat("_" + tribe));
    }

    private boolean checkIfGreatChampionsAllowed(UnitCard unitCard, List<DemonWorldCard> armyList) {

        boolean result = true;

        if (Arrays.stream(GREAT_CHAMPION_NAMES).anyMatch(name -> name.equalsIgnoreCase(unitCard.getUnitName()))) {

            for (String tribe : TRIBES) {
                if (!(armyList.stream().anyMatch(c -> c.getSubFaction().contains(tribe)))) {

                    result = false;
                    break;
                }
            }

        }
        return result;
    }


    @Override
    public boolean commanderPresent(List<DemonWorldCard> list) {
        return list.stream().
                filter(c -> c instanceof UnitCard).anyMatch(card -> ((UnitCard) card).getCommandStars() >= 2);
    }
}
