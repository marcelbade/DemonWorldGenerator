package marcel.demonworld.armygenerator.GameLogic.armieCalculators;

import static marcel.demonworld.armygenerator.GameLogic.constants.SubFactions.empire.EmpireSubFaction.*;

import marcel.demonworld.armygenerator.dto.armyDTOs.CalculatedArmyResult;
import marcel.demonworld.armygenerator.dto.statCardDTOs.DemonWorldCard;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;

import java.util.List;


public class EmpireCalculator implements ArmyCalculator {

    private boolean flagkaiserheer = false;
    private boolean flagorden = false;
    private boolean flaghelden_befehlshaber = false;
    private boolean flagmagier_priester = false;
    private boolean flagzentralmark = false;
    private boolean flagnordmark = false;
    private boolean flagsuedmark = false;
    private boolean flagostmark = false;
    private boolean flagwestmark = false;

    private int totalSum = 0;
    private int kaiserheerSum = 0;
    private int ordenSum = 0;
    private int helden_befehlshaberSum = 0;
    private int magier_priesterSum = 0;
    private int zentralmarkSum = 0;
    private int nordmarkSum = 0;
    private int suedmarkSum = 0;
    private int ostmarkSum = 0;
    private int westmarkSum = 0;

    @Override
    public CalculatedArmyResult CalculatePointCost(List<DemonWorldCard> list, float maximumPointValue) {

        for (DemonWorldCard uc : list) {

            switch (uc.getSubFaction()) {
                case NORDMARK:
                    break;
                case SUEDMARK:
                    break;
                case WESTMARK:
                    break;
                case OSTMARK:
                    break;
                case ZENTRALMARK:
                    break;
                case ORDEN:
                    break;
                case KAISERHEER:
                    break;
                case MAGIER_PRIESTER:
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
