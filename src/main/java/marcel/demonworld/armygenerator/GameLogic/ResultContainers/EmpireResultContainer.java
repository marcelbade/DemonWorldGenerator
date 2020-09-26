package marcel.demonworld.armygenerator.GameLogic.ResultContainers;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmpireResultContainer {

    String pickedMark;

    private boolean flagKaiserheer = false;
    private boolean flagProvinzHeer = false;
    private boolean flagOrden = false;
    private boolean flagHelden_Befehlshaber = false;
    private boolean flagMagier_Priester = false;
    private boolean flagZentralmark = false;
    private boolean flagNordmark = false;
    private boolean flagSuedmark = false;
    private boolean flagOstmark = false;
    private boolean flagWestmark = false;
    private boolean ArmyFlag = false;

    private int totalSum = 0;
    private int kaiserheerSum = 0;
    private int provinzheerSum = 0;
    private int ordenSum = 0;
    private int helden_befehlshaberSum = 0;
    private int magier_priesterSum = 0;
    private int zentralmarkSum = 0;
    private int nordmarkSum = 0;
    private int suedmarkSum = 0;
    private int ostmarkSum = 0;
    private int westmarkSum = 0;

}
