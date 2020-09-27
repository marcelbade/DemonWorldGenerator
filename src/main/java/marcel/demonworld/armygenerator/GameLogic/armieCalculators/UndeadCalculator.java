package marcel.demonworld.armygenerator.GameLogic.armieCalculators;

import marcel.demonworld.armygenerator.GameLogic.ResultContainers.UndeadResultContainer;
import marcel.demonworld.armygenerator.dto.armyDTOs.CalculatedArmyResult;
import marcel.demonworld.armygenerator.dto.statCardDTOs.DemonWorldCard;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static marcel.demonworld.armygenerator.GameLogic.constants.SubFactions.undead.UndeadSubFaction.*;


/* TODO SQL: the giant mummy and the "Werkstatt", "altar der Totenbeschwörer" are unique!
 *
 *
 * */
public class UndeadCalculator implements ArmyCalculator {

    @Autowired
    UndeadResultContainer container;

    @Override
    public CalculatedArmyResult CalculatePointCost(List<DemonWorldCard> list, float maximumPointValue) {
        for (DemonWorldCard uc : list) {


            switch (uc.getSubFaction()) {

                case KLEINER_BUND:
                    container.setKleinerBundSum(container.getKleinerBundSum() + uc.getPoints());

                    if (container.getKleinerBundSum() >= maximumPointValue * 0.20 && container.getKleinerBundSum() <= maximumPointValue * 0.70) {
                        container.setFlagKleinerBund(true);
                    }
                    break;
                case GROSSER_BUND:
                    container.setGrossserBundSum(container.getGrossserBundSum() + uc.getPoints());

                    if (container.getGrossserBundSum() <= maximumPointValue * 0.15 && container.getGrossserBundSum() <= maximumPointValue * 0.50) {
                        container.setFlagGrosserBund(true);
                    }
                    break;
                case SCHATTENBUND:
                    container.setSchattenBundSum(container.getSchattenBundSum() + uc.getPoints());

                    if (container.getSchattenBundSum() <= maximumPointValue * 0.30) {
                        container.setFlagSchattenbund(true);
                    }
                    break;
                case MAGIER:
                    container.setMagierSum(container.getMagierSum() + uc.getPoints());

                    if (container.getMagierSum() <= maximumPointValue * 0.40) {
                        container.setFlagMagier(true);
                    }
                    break;
                case HELDEN_BEFEHLSHABER:
                    container.setHelden_BefehlshaberSum(container.getHelden_BefehlshaberSum() + uc.getPoints());

                    if (container.getHelden_BefehlshaberSum() <= maximumPointValue * 0.40) {
                        container.setFlagHelden_Befehlshaber(true);
                    }
                    break;
                case ISHTAKALLIIERTE:
                    container.setIshtakAllierterSum(container.getIshtakAllierterSum() + uc.getPoints());

                    if (container.getIshtakAllierterSum() <= maximumPointValue * 0.20) {
                        container.setFlagIshtakAllierte(true);
                    }
                    break;

            }

        }
        if (container.getTotalSum() <= maximumPointValue
                && commanderPresent(list)
                && determinePointValuesHeroes(list, maximumPointValue)) {
            container.setArmyFlag(true);
        }
        return null;
    }

    // all heroes, spellcasters,... must be <= 50% of the army
    private boolean determinePointValuesHeroes(List<DemonWorldCard> armyList, float maximumPointValue) {

        return armyList.stream().
                filter(c -> ((UnitCard) c).getUnitType().equalsIgnoreCase("H|M")).
                mapToInt(DemonWorldCard::getPoints).sum() <= maximumPointValue * 0.50;
    }


    // in an undead army, you need a ** commander OR a necromancer
    @Override
    public boolean commanderPresent(List<DemonWorldCard> list) {
        return list.stream().anyMatch(c -> ((UnitCard) c).getCommandStars() >= 2 || c.getSubFaction().equalsIgnoreCase("Totenbeschwörer"));
    }
}
