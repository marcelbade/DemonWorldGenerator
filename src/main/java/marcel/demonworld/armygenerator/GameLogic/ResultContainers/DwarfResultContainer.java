package marcel.demonworld.armygenerator.GameLogic.ResultContainers;


//TODO: important -> when the dwarfs are selected from a dropdown menu, one realm must be picked. When picked
// one instance of this class must be sent to the backend and contain the setting for the picked realm

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DwarfResultContainer {

    //picked Realm

    String pickedRealm;

    // rule compliance flags
    private boolean flagGeneraltroops = false;
    private boolean flagGaeta = false;
    private boolean flagZahra = false;
    private boolean flagHeroes_characters_priests = false;
    private boolean flagAllies = false;
    private boolean ArmyFlag = false;

    // point total for the entire army and all subfactions
    int totalSum = 0;
    private int generaltroopsSum = 0;
    private int gaetaSum = 0;
    private int zahraSum = 0;
    private int heroes_characters_priestsSum = 0;
    private int alliesSum = 0;


}
