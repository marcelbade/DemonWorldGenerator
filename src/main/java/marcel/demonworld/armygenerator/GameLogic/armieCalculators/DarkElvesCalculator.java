package marcel.demonworld.armygenerator.GameLogic.armieCalculators;

import marcel.demonworld.armygenerator.GameLogic.ResultContainers.DarkElvesResultContainer;
import marcel.demonworld.armygenerator.dto.armyDTOs.CalculatedArmyResult;
import marcel.demonworld.armygenerator.dto.statCardDTOs.DemonWorldCard;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import org.springframework.beans.factory.annotation.Autowired;

import static marcel.demonworld.armygenerator.GameLogic.constants.SubFactions.darkelves.DarkElvesSubFaction.*;

import java.util.List;


/*
 * additonal rules
 *  - sum for all heroes, priests, magicians, ect <= 50%
 * TODO: only one "Blutzierkel", Blutaltar, hat'Naris, fix this in sql!! UNIQUE FLAG!
 *
 *
 * */
public class DarkElvesCalculator implements ArmyCalculator {

    @Autowired
    DarkElvesResultContainer container;

    @Override
    public CalculatedArmyResult CalculatePointCost(List<DemonWorldCard> list, float maximumPointValue) {

        for (DemonWorldCard uc : list) {

            switch (uc.getSubFaction()) {
                case ADELSKASTE:
                    container.setAdelskasteSum(container.getAdelskasteSum() + uc.getPoints());

                    if (container.getAdelskasteSum() <= maximumPointValue * .30) {
                        container.setFlagAdelskaste(true);
                    }
                    break;
                case KRIEGERKASTE:
                    container.setKriegerkasteSum(container.getKriegerkasteSum() + uc.getPoints());

                    if (container.getKriegerkasteSum() <= maximumPointValue * .30) {
                        container.setFlagKriegerkaste(true);
                    }
                    break;
                case MAGIERKASTE:
                    container.setMagierkasteSum(container.getMagierkasteSum() + uc.getPoints());

                    if (container.getMagierkasteSum() <= maximumPointValue * .30) {
                        container.setFlagMagierkaste(true);
                    }
                    break;
                case PRIESTERKASTE:
                    container.setPriesterkasteSum(container.getPriesterkasteSum() + uc.getPoints());

                    if (container.getPriesterkasteSum() <= maximumPointValue * .30) {
                        container.setFlagPriesterkaste(true);
                    }
                    break;
                case MAGIER_PRIESTERINNEN:
                    container.setMagier_priesterinnenSum(container.getMagier_priesterinnenSum() + uc.getPoints());

                    if (container.getMagier_priesterinnenSum() <= maximumPointValue * .30) {
                        container.setFlagMagier_priesterinnen(true);
                    }
                    break;
                case HELDEN_BEFEHLSHABER:
                    container.setHelden_befehlshaberSum(container.getHelden_befehlshaberSum() + uc.getPoints());

                    if (container.getHelden_befehlshaberSum() <= maximumPointValue * .30) {
                        container.setFlagHelden_befehlshaber(true);
                    }
                    break;

            }

            if (container.getTotalSum() <= maximumPointValue
                    && commanderPresent(list)
                    && checkHeroesLimit(maximumPointValue)) {
                container.setArmyFlag(true);
            }
        }
        return null;
    }

    private boolean checkHeroesLimit(float maximumPointValue) {
        return (container.getMagier_priesterinnenSum() + container.getHelden_befehlshaberSum()) <= maximumPointValue;
    }

    @Override
    public boolean commanderPresent(List<DemonWorldCard> list) {
        return list.stream().filter(c -> c instanceof UnitCard).anyMatch(card -> ((UnitCard) card).getCommandStars() >= 2);
    }
}


