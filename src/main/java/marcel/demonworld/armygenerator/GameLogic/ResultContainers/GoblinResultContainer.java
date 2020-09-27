package marcel.demonworld.armygenerator.GameLogic.ResultContainers;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GoblinResultContainer implements ResultContainer{

    private boolean flagInfanterie = false;
    private boolean flagInsektenreiter = false;
    private boolean flagRieseninsekten = false;
    private boolean flagGeraete = false;
    private boolean flagSchamanen = false;
    private boolean flagverbuendete_Orktruppen = false;
    private boolean flagHelden_Befehlshaber = false;
    private boolean ArmyFlag = false;

    // net and subfaction totals
    int totalSum = 0;
    int infanterieSum = 0;
    int insektenreiterSum = 0;
    int rieseninsektenSum = 0;
    int geraeteSum = 0;
    int verbuendete_OrktruppenSum = 0;
    int schamanenSum = 0;
    int helden_BefehlshaberSum = 0;

}
