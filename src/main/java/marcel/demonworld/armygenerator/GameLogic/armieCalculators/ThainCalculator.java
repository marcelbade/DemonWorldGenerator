package marcel.demonworld.armygenerator.GameLogic.armieCalculators;

import marcel.demonworld.armygenerator.dto.armyDTOs.CalculatedArmyResult;

import static marcel.demonworld.armygenerator.GameLogic.constants.SubFactions.thain.ThainSubFaction.*;

import marcel.demonworld.armygenerator.dto.statCards.DemonWorldCard;
import marcel.demonworld.armygenerator.dto.statCards.UnitCard;

import java.util.List;

public class ThainCalculator implements ArmyCalculator {
    private boolean flagStammeskrieger = false;
    private boolean flagVeteranen = false;
    private boolean flagSchamanen = false;
    private boolean flagGarydwen = false;
    private boolean flagKirche = false;
    private boolean flagChampions_helden = false;

    private int totalSum = 0;
    private int stammeskriegerSum = 0;
    private int flagveteranenSum = 0;
    private int schamanenSum = 0;
    private int garydwenSum = 0;
    private int kircheSum = 0;
    private int champions_heldenSum = 0;

    @Override
    public CalculatedArmyResult CalculatePointCost(List<DemonWorldCard> list, float maximumPointValue) {

        for (DemonWorldCard uc : list) {

            switch (uc.getSubFaction()) {
                case SCHAMANEN:
                    break;
                case KIRCHE:
                    break;
                case STAMMESKRIEGER:
                    break;
                case CHAMPIONS_HELDEN:
                    break;
                case GARYDWEN:
                    break;
            }

        }


        return null;
    }

    @Override
    public boolean commanderPresent(List<DemonWorldCard> list) {
        for (DemonWorldCard dc : list) {
            if (dc instanceof UnitCard && ((UnitCard) dc).getCommandStars() >= 2) {
                return true;
            }
        }
        return false;
    }
}
