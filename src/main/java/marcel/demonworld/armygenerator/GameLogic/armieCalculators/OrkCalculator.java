package marcel.demonworld.armygenerator.GameLogic.armieCalculators;

import marcel.demonworld.armygenerator.dto.armyDTOs.CalculatedArmyResult;
import marcel.demonworld.armygenerator.dto.statCardDTOs.DemonWorldCard;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;

import java.util.List;

import static marcel.demonworld.armygenerator.GameLogic.constants.SubFactions.orks.OrksSubFaction.*;

public class OrkCalculator implements ArmyCalculator {

    private boolean flagEinheiten = false;
    private boolean flagHelden_Befehlshaber = false;
    private boolean flagZauberer = false;
    private boolean flagGiganten = false;
    private boolean flagHGeraete = false;
    private boolean flagSondertruppen = false;
    private boolean flagClanngett = false;

    private int totalSum = 0;
    private int helden_BefehlshaberSum = 0;
    private int zaubererSum = 0;
    private int gigantenSum = 0;
    private int geraeteSum = 0;
    private int sondertruppenSum = 0;
    private int clanngettnSum = 0;

    @Override
    public CalculatedArmyResult CalculatePointCost(List<DemonWorldCard> list, float maximumPointValue) {

        for (DemonWorldCard uc : list) {

            switch (uc.getSubFaction()) {
                case SONDERTRUPPEN:
                    break;
                case ZAUBERER:
                    break;
                case CLANNGETT:
                    break;
                case HELDEN_BEFEHLSHABER:
                    break;
                case GERAETE:
                    break;
                case GIGANTEN:
                    break;
                case EINHEITEN:
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
