package marcel.demonworld.armygenerator.GameLogic.armieCalculators;

import marcel.demonworld.armygenerator.GameLogic.ResultContainers.ThainResultContainer;
import marcel.demonworld.armygenerator.dto.armyDTOs.CalculatedArmyResult;

import static marcel.demonworld.armygenerator.GameLogic.constants.SubFactions.thain.ThainSubFaction.*;

import marcel.demonworld.armygenerator.dto.statCardDTOs.DemonWorldCard;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import org.springframework.beans.factory.annotation.Autowired;

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

                    if (container.getVeteranenSum() <= maximumPointValue * .50) {
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

                    if (container.getChampions_heldenSum() <= maximumPointValue * .30) {
                        container.setFlagChampions_helden(true);
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


    public Map<String, Integer> maxNumberOfVeteransOfTribe(UnitCard card) {

        //todo: check the tribes in sql

        Map<String, Integer> numberOfTribeUnits = new HashMap<>();
        numberOfTribeUnits.put("Eagle", 0);
        numberOfTribeUnits.put("Mountainlion", 0);
        numberOfTribeUnits.put("Boar", 0);
        numberOfTribeUnits.put("Bear", 0);
        numberOfTribeUnits.put("Wolf", 0);

        for (Map.Entry<String, Integer> entry : numberOfTribeUnits.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(card.getSubFaction())) {
                entry.setValue(entry.getValue() + 1);
            }
        }
        return numberOfTribeUnits;
    }

    /**
     * ALL units that are not Dorgar, Garydwen must be assigned to a tribe at the moment of selection.
     *
     * @param unit  the unit that must be assigned to a tribe.
     * @param tribe the tribe it is assigned to.
     */
    public void assignTribeToUnit(UnitCard unit, String tribe) {
        unit.setSubFaction(tribe.concat("_zugeordnet"));
    }

    /**
     * determine the number of max champions per tribe
     *
     * @param list current army list
     * @param card
     * @return number of champions
     */
    private long setMaxNumberOfChampionsOfTribe(List<DemonWorldCard> list, UnitCard card) {
        return list.stream().filter(c -> c.getSubFaction().equalsIgnoreCase(card.getSubFaction())).count();
    }

    @Override
    public boolean commanderPresent(List<DemonWorldCard> list) {
        return list.stream().filter(c -> c instanceof UnitCard).anyMatch(card -> ((UnitCard) card).getCommandStars() >= 2);
    }
}
