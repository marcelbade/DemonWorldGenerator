package marcel.demonworld.armygenerator.GameLogic.ResultContainers;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmpireResultContainer {

    String pickedMark;

    private boolean flagkaiserheer = false;
    private boolean flagorden = false;
    private boolean flaghelden_befehlshaber = false;
    private boolean flagmagier_priester = false;
    private boolean flagzentralmark = false;
    private boolean flagnordmark = false;
    private boolean flagsuedmark = false;
    private boolean flagostmark = false;
    private boolean flagwestmark = false;
    private boolean ArmyFlag = false;

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

}
