package marcel.demonworld.armygenerator.GameLogic.ResultContainers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UndeadResultContainer {


    private boolean flagKleinerBund = false;
    private boolean flagGrosserBund = false;
    private boolean flagMagier = false;
     private boolean flagHelden_Befehlshaber = false;
    private boolean flagSchattenbund = false;
    private boolean flagIshtakAllierte = false;
    private boolean ArmyFlag = false;

    private int totalSum = 0;
    private int kleinerBundSum = 0;
    private int magierSum = 0;
    private int schattenBundSum = 0;
    private int grossserBundSum = 0;
    private int ishtakAllierterSum = 0;
    private int helden_BefehlshaberSum = 0;
}
