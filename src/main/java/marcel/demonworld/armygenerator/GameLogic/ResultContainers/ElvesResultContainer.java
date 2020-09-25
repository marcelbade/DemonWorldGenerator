package marcel.demonworld.armygenerator.GameLogic.ResultContainers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ElvesResultContainer {

    // rule compliance flags
    private boolean flagThanarilClantruppen = false;
    private boolean flagThanarilKriegerbünde = false;
    private boolean flagThanaril_Clanlords_Barden_Befehlshaber = false;
    private boolean flagAlte_Helden = false;
    private boolean flagDyrea_Loreaths = false;
    private boolean flagOrea_Vanar_Truppen_Meister = false;
    private boolean flagRatsarmee_Einheiten_Befehlshaber = false;
    private boolean flagBaumherren_Zentauren = false;
    private boolean ArmyFlag = false;
    // does the army have a valid commander (2 Stars minimum) ?
    private boolean cmdrFlag = false;


    // point total for the entire army and all subfactions
    private int totalSum = 0;
    private int ThanarilClantruppenSum = 0;
    private int ThanarilKriegerbuendeSum = 0;
    private int Thanaril_Clanlords_Barden_BefehlshaberSum = 0;
    private int Alte_HeldenSum = 0;
    private int numberOfOldHeroes;
    private int Dyrea_LoreathsSum = 0;
    private int Orea_Vanar_Truppen_MeisterSum = 0;
    private int Ratsarmee_Einheiten_BefehlshaberSum = 0;
    private int Baumherren_ZentaurenSum = 0;
}
