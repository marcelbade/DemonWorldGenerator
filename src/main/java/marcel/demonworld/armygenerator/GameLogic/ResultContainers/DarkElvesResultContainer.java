package marcel.demonworld.armygenerator.GameLogic.ResultContainers;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DarkElvesResultContainer {

    private boolean flagKriegerkaste = false;
    private boolean flagAdelskaste = false;
    private boolean flagMagierkaste = false;
    private boolean flagPriesterkaste = false;
    private boolean flagHelden_befehlshaber = false;
    private boolean flagMagier_priesterinnen = false;
    private boolean ArmyFlag = false;

    private int totalSum = 0;
    private int kriegerkasteSum = 0;
    private int magierkasteSum = 0;
    private int adelskasteSum = 0;
    private int priesterkasteSum = 0 ;
    private int Helden_befehlshaberSum = 0;
    private int magier_priesterinnenSum = 0;


}
