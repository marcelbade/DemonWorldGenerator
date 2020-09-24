package marcel.demonworld.armygenerator.GameLogic.armieCalculators;

import java.lang.reflect.Field;

/**
 *
 */
public class ExperimentalReflectionFlagTester {

    /**
     * MIGHT NOT WORK AT ALL! -> this is a reflection powered method that looks at all fields,
     * makes them temporarily public and checks the name to see if they're a "rule compliance flag",
     * i.e. one of the boolean values that store whether the army list is compliant with one rule (
     * all boolean values in the ArmyCalculator implementations have a name starting with "flag" for his reason).
     * <p>
     * * As soon as one of the checked flags returns a value of false, the loop stops and FALSE is returned, indicating
     *
     * @param calc an ArmyCalculator implementation
     * @return a boolean flag that is true only if
     */
    public static boolean determineAllBooleanValues(ArmyCalculator calc) {

        Field[] flags = calc.getClass().getDeclaredFields();
        String fieldName;
        Boolean AllFlags = true;

        for (Field f : flags) {
            f.setAccessible(true);
            fieldName = f.getName();
            if (fieldName.contains("flag")) {
                try {
                    AllFlags = (Boolean) f.get(fieldName);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (!AllFlags)
                    return AllFlags;
            }
        }
        return AllFlags;
    }// end method
}



