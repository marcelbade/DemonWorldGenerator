package marcel.demonworld.armygenerator.GameLogic.armieCalculators;

import marcel.demonworld.armygenerator.GameLogic.ResultContainers.NorwingerResultContainer;
import marcel.demonworld.armygenerator.dto.armyDTOs.CalculatedArmyResult;
import marcel.demonworld.armygenerator.dto.statCardDTOs.DemonWorldCard;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import org.springframework.beans.factory.annotation.Autowired;

import static marcel.demonworld.armygenerator.GameLogic.constants.SubFactions.Norwinger.NorwingerSubFactions.*;

import java.util.List;

/*
 *  additional rules:
 * - sturmlords, hexen, helden,... -> max. 50%
 * - for every Neanders unit ->  one other human unit...
 * - todo: SQL: Jäger des Nordens is unique!!!
 * - giant yeti -> at least one unit yeti!
 * */
public class NorwingerCalculator implements ArmyCalculator {

    @Autowired
    NorwingerResultContainer container;


    @Override
    public CalculatedArmyResult CalculatePointCost(List<DemonWorldCard> list, float maximumPointValue) {

        for (DemonWorldCard uc : list) {

            switch (uc.getSubFaction()) {

                case BARBAREN:
                    container.setBarbarenSum(container.getBarbarenSum() + uc.getPoints());

                    if (container.getBarbarenSum() >= maximumPointValue * 0.20 && container.getBarbarenSum() <= maximumPointValue * 0.75) {
                        container.setFlagBarbaren(true);
                    }
                    break;
                case VERBÜNDETE:
                    container.setVerbuendeteSum(container.getVerbuendeteSum() + uc.getPoints());

                    if (container.getVerbuendeteSum() <= maximumPointValue * 0.25) {
                        container.setFlagVerbuendete(true);
                    }
                    break;
                case VETERANEN:
                    container.setVeteranenSum(container.getVeteranenSum() + uc.getPoints());

                    if (container.getVeteranenSum() <= maximumPointValue * 0.40) {
                        container.setFlagVeteranen(true);
                    }
                    break;
                case STURMLORDS_HEXEN:
                    container.setSturmlords_HexenSum(container.getSturmlords_HexenSum() + uc.getPoints());

                    if (container.getSturmlords_HexenSum() <= maximumPointValue * 0.30) {
                        container.setFlagSturmlords_Hexen(true);
                    }
                    break;
                case MÄCHTIGE_WESEN:
                    container.setMaechtigeWesenSum(container.getMaechtigeWesenSum() + uc.getPoints());

                    if (container.getMaechtigeWesenSum() <= maximumPointValue * 0.40) {
                        container.setFlagMaechtigeWesen(true);
                    }
                    break;
                case HELDEN_BEFEHLSHABER:
                    container.setHelden_BefehlshaberSum(container.getHelden_BefehlshaberSum() + uc.getPoints());

                    if (container.getHelden_BefehlshaberSum() <= maximumPointValue * 0.30) {
                        container.setFlagHelden_Befehlshaber(true);
                    }
                    break;


            }
        }
        if (container.getTotalSum() <= maximumPointValue
                && commanderPresent(list)
                && determinePointValuesHeroesMagicians(list, maximumPointValue)) {
            container.setArmyFlag(true);
        }

        return null;
    }

    // all single character, magicians, ect. must not exceed 50% of the army
    private boolean determinePointValuesHeroesMagicians(List<DemonWorldCard> armyList, float maximumPointValue) {

        return armyList.stream().
                filter(c -> ((UnitCard) c).getUnitType().equalsIgnoreCase("H|M")).
                mapToInt(DemonWorldCard::getPoints).sum() <= maximumPointValue * 0.50;
    }

    /**
     * Tests the yeti requirement for an army
     *
     * @param armyList
     * @return true, unless a giant yeti is present but no yeti warriors.
     */
    private boolean assertYetiRule(List<DemonWorldCard> armyList) {
        boolean result = true;
        if (armyList.stream().anyMatch(c -> c.getName().equalsIgnoreCase("Riesenyeti"))) {
            result = armyList.stream().anyMatch(c -> c.getName().equalsIgnoreCase("Yetikrieger"));
        }
        return result;
    }

    /**
     * Tests the Neander requirement for an army
     *
     * @param armyList
     * @return true, unless there is a number of Neander units but not an equal or higher number of other human units.
     */
    private boolean assertNeanderRule(List<DemonWorldCard> armyList) {

        long neanderNumber = armyList.stream().filter(c -> c.getName().equalsIgnoreCase("Neanders")).count();
        long humanNumber = armyList.stream().filter(c -> c.getName().equalsIgnoreCase("Mammutjäge|Plänkler|Wilde Krieger|Schneebarbaren")).count();

        return neanderNumber < humanNumber;
    }


    @Override
    public boolean commanderPresent(List<DemonWorldCard> list) {
        return list.stream().filter(c -> c instanceof UnitCard).anyMatch(card -> ((UnitCard) card).getCommandStars() >= 2);
    }
}
