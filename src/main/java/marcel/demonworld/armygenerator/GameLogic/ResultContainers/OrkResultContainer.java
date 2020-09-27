package marcel.demonworld.armygenerator.GameLogic.ResultContainers;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrkResultContainer implements ResultContainer{

    //todo: this has to be set on the frontend to select the correct ork calculator.
        boolean clangettSelected = false;

    private boolean flagEinheiten = false;
    private boolean flagHelden_Befehlshaber = false;
    private boolean flagZauberer = false;
    private boolean flagGiganten = false;
    private boolean flagGeraete = false;
    private boolean flagSondertruppen = false;
    private boolean flagClanngett = false;
    private boolean ArmyFlag = false;
    private boolean orkGrundEinheitenFlag = false;

    private int totalSum = 0;
    private int helden_BefehlshaberSum = 0;
    private int zaubererSum = 0;
    private int gigantenSum = 0;
    private int geraeteSum = 0;
    private int sondertruppenSum = 0;
    private int clanngettSum = 0;
    private int orkGrundEinheitenSum = 0;

}
