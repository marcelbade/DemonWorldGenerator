package marcel.demonworld.armygenerator.GameLogic.armieCalculators;

import marcel.demonworld.armygenerator.dto.armyDTOs.CalculatedArmyResult;
import marcel.demonworld.armygenerator.dto.statCards.DemonWorldCard;
import marcel.demonworld.armygenerator.dto.statCards.UnitCard;

import java.util.List;

import static marcel.demonworld.armygenerator.GameLogic.constants.SubFactions.ishtar.IshtarSubFaction.*;

public class IshtarCalculator implements ArmyCalculator {

    private boolean flagEishexen = false;
    private boolean flagEisriesen = false;
    private boolean flagMenschen = false;
    private boolean flagDaemonen = false;
    private boolean flagUntote = false;
    private boolean flagTiermenschen = false;

    private int totalSum = 0;
    private int eishexenSum = 0;
    private int menschenSum = 0;
    private int eisriesenSum = 0;
    private int daemonenSum = 0;
    private int untoteSum = 0;
    private int tiermenschenSum = 0;

    @Override
    public CalculatedArmyResult CalculatePointCost(List<DemonWorldCard> list, float maximumPointValue) {


        for (DemonWorldCard uc : list) {

            switch (uc.getSubFaction()) {
                case EISHEXEN:
                    break;
                case EISRIESEN:
                    break;
                case DAEMONEN:
                    break;
                case UNTOTE:
                    break;
                case TIERMENSCHEN:
                    break;
                case MENSCHEN:
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
