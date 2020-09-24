package marcel.demonworld.armygenerator.GameLogic.armieCalculators;

import java.lang.reflect.Field;

public class ArmyCompliance {

    /**
     * Method uses reflection to check if ALL compliance flags for an army are set to true. If this is not the case,
     * a flag is set to false and returned.
     *
     * @param ac An ArmyCalculator implementation
     * @return A boolean reflecting whether the entire list is rule compliant.
     */
    public static boolean checkALlComplianceFlags(ArmyCalculator ac) {

        String fieldName;

        // value for army flag
        boolean compliance = true;

        Field[] fields = ac.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            fieldName = f.getName();
            if (fieldName.contains("flag")) {
                try {
                    compliance = f.getBoolean(fieldName);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (!compliance) {
                    // if one flag is false, break the loop and return false
                    return compliance;
                }
            }
        }// end loop
        return compliance;
    }
}
