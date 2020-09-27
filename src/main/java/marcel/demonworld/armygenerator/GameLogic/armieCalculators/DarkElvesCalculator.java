package marcel.demonworld.armygenerator.GameLogic.armieCalculators;

import marcel.demonworld.armygenerator.dto.armyDTOs.CalculatedArmyResult;
import marcel.demonworld.armygenerator.dto.statCardDTOs.DemonWorldCard;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;

import static marcel.demonworld.armygenerator.GameLogic.constants.SubFactions.darkelves.DarkElvesSubFaction.*;

import java.util.List;

public class DarkElvesCalculator implements ArmyCalculator {


    private boolean flagKriegerkaste = false;
    private boolean flagAdelskaste = false;
    private boolean flagMagierkaste = false;
    private boolean flagPriesterkaste = false;
    private boolean flagHelden_befehlshaber = false;
    private boolean flagMagier_priesterinnen = false;

    private int totalSum = 0;
    private int kriegerkasteSum = 0;
    private int MagierkasteSum = 0;
    private int adelskasteSum = 0;
    private int Helden_befehlshaberSum = 0;
    private int magier_priesterinnenSum = 0;

    @Override
    public CalculatedArmyResult CalculatePointCost(List<DemonWorldCard> list, float maximumPointValue) {

        for (DemonWorldCard uc : list) {

            switch (uc.getSubFaction()) {
                case ADELSKASTE:
                    break;
                case KRIEGERKASTE:
                    break;
                case MAGIERKASTE:
                    break;
                case PRIESTERKASTE:
                    break;
                case MAGIER_PRIESTERINNEN:
                    break;
                case HELDEN_BEFEHLSHABER:
                    break;

            }
        }
        return null;
    }
    @Override
    public boolean commanderPresent(List<DemonWorldCard> list) {
        return list.stream().filter(c -> c instanceof UnitCard).anyMatch(card -> ((UnitCard) card).getCommandStars() >= 2);
    }
}


