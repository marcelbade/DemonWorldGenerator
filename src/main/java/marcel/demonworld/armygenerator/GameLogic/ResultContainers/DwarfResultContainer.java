package marcel.demonworld.armygenerator.GameLogic.ResultContainers;


//TODO: important -> when the dwarfs are selected from a dropdown menu, one realm must be picked. When picked
// one instance of this class must be sent to the backend and contain the setting for the picked realm

import lombok.Getter;
import lombok.Setter;
import marcel.demonworld.armygenerator.dto.statCardDTOs.DemonWorldCard;

import java.lang.reflect.Field;
import java.util.*;

@Getter
@Setter
public class DwarfResultContainer implements ResultContainer {

    //The Army List
    List<DemonWorldCard> armyList = new ArrayList<>();

    //picked Realm

    boolean gaetaPicked;

    // rule compliance flags
    private boolean flagGeneraltroops = false;
    private boolean flagGaeta = false;
    private boolean flagZahra = false;
    private boolean flagHeroes_characters_priests = false;
    private boolean flagAllies = false;
    private boolean commanderPresentFlag = false;
    private boolean ArmyFlag = false;

    // point total for the entire army and all subfactions
    int totalSum = 0;
    private int generaltroopsSum = 0;
    private int gaetaSum = 0;
    private int zahraSum = 0;
    private int heroes_characters_priestsSum = 0;
    private int alliesSum = 0;


    // TODO: if the reflection does not work, redo the flags and sums as maps and use this!
    public void resetArmyList(Map<String, Boolean> flags, Map<String, Integer> sums) {

        flags.forEach((s, b) -> {
            b = false;
        });
        sums.forEach((s, i) -> {
            i = 0;
        });
    }


    /**
     * TODO EXPERIMENTAL: reflection method resets every field in result container. Test & implement for every army if successful.
     * TODO [cont.] if it doesn't work (50/50 ? :) ), implement a map for flags and sums!
     *
     * @throws IllegalAccessException
     */
    public void resetArmyList() throws IllegalAccessException {
        Field[] flags = this.getClass().getDeclaredFields();
        for (Field f : flags) {
            f.setAccessible(true);
            if (f.getType() == Boolean.class) {
                f.set(this, false);
            } else if (f.getType() == Integer.class) {
                f.set(this, 0);
            }
        }
    }

    /**
     * TODO EXPERIMENTAL: reflection method goes through all the checks for army compliance (all flags here) and fills
     * TODO [cont.] them into a map with the flags name and the boolean value
     *
     * @return
     * @throws IllegalAccessException
     */
    public Map<String, Boolean> testCompliance() throws IllegalAccessException {

        Map<String, Boolean> result = new HashMap<>();

        Field[] flags = this.getClass().getDeclaredFields();
        for (Field f : flags) {
            f.setAccessible(true);
            if (f.getType() == Boolean.class) {
                result.put(f.getName(), f.getBoolean(this));
            }
        }
        return result;
    }
}


