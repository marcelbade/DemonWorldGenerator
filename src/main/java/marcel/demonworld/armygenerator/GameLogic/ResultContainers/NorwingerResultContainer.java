package marcel.demonworld.armygenerator.GameLogic.ResultContainers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NorwingerResultContainer {

    private boolean flagVerbuendete = false;
    private boolean flagMaechtigeWesen = false;
    private boolean flagVeteranen = false;
    private boolean flagBarbaren = false;
    private boolean flagHelden_Befehlshaber = false;
    private boolean flagSturmlords_Hexen = false;
    private boolean ArmyFlag = false;

    private int totalSum = 0;
    private int VerbuendeteSum = 0;
    private int VeteranenSum = 0;
    private int BarbarenSum = 0;
    private int Helden_BefehlshaberSum = 0;
    private int Sturmlords_HexenSum = 0;
    private int MaechtigeWesenSum = 0;

}
