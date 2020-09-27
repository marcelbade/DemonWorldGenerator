package marcel.demonworld.armygenerator.GameLogic.ResultContainers;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ThainResultContainer implements ResultContainer {


    private boolean flagStammeskrieger = false;
    private boolean flagVeteranen = false;
    private boolean flagSchamanen = false;
    private boolean flagGarydwen = false;
    private boolean flagKirche = false;
    private boolean flagChampions_helden = false;
    private boolean armyFlag = false;

    private int totalSum = 0;
    private int stammeskriegerSum = 0;
    private int veteranenSum = 0;
    private int flagveteranenSum = 0;
    private int schamanenSum = 0;
    private int garydwenSum = 0;
    private int kircheSum = 0;
    private int champions_heldenSum = 0;
}
