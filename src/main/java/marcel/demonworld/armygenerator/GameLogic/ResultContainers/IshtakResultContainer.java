package marcel.demonworld.armygenerator.GameLogic.ResultContainers;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IshtakResultContainer {

    private boolean flagEishexen = false;
    private boolean flagEisriesen = false;
    private boolean flagMenschen = false;
    private boolean flagDaemonen = false;
    private boolean flagUntote = false;
    private boolean flagTiermenschen = false;
    private boolean armyFlag = false;


    private int totalSum = 0;
    private int eishexenSum = 0;
    private int menschenSum = 0;
    private int eisriesenSum = 0;
    private int daemonenSum = 0;
    private int untoteSum = 0;
    private int tiermenschenSum = 0;

}
