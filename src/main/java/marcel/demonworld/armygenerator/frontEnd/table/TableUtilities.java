package marcel.demonworld.armygenerator.frontEnd.table;

import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import marcel.demonworld.armygenerator.services.SelectArmyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TableUtilities {

    @Autowired
    SelectArmyService service;

    public String[] getTableAttributeNames() {

        List<String> result = new ArrayList<>();
        String[] names = new String[UnitCard.class.getDeclaredFields().length];

        UnitCard uc = new UnitCard();

        Arrays.stream(uc.getClass().getDeclaredFields()).forEach(f -> f.setAccessible(true));
        Arrays.stream(uc.getClass().getDeclaredFields()).forEach(f -> result.add(f.getName()));

        for (int i = 0; i < names.length; i++) {
            names[i] = result.get(i);
        }

        return names;
    }

    /**
     * KEY METHOD. This method remaps your repo DTO to a 2D-array that Swing uses for tables.
     *
     * @param faction -String, faction to display
     * @return a 2D String array [card][stats]
     * @throws IllegalAccessException throws exceprion
     */
    public String[][] createTableData(String faction) throws IllegalAccessException {

        // return array size calculated via reflection
        String[][] result = new String[service.returnAll().size()][UnitCard.class.getDeclaredFields().length];

        //get data
        List<UnitCard> armyList = service.returnArmy(faction);

        for (int i = 0; i < armyList.size(); i++) {

            UnitCard uc = armyList.get(i);

            for (int j = 0; j < UnitCard.class.getDeclaredFields().length; j++) {

                Field[] stats = uc.getClass().getDeclaredFields();
                Field f = stats[j];
                f.setAccessible(true);
                result[i][j] = beautifyStat(turnFieldToString(f.get(uc)));

            }
        }
        return result;
    }

    /**
     * Method turns any field value of an Object implementing DemonCard into String.
     *
     * @param o reflected property of Class
     * @return Stringified value of class field.
     */
    private String turnFieldToString(Object o) {
        if (o instanceof Integer) {
            return Integer.toString((int) o);
        } else if (o instanceof Boolean) {
            return Boolean.toString((boolean) o);
        }
        return (String) o;
    }


    private String beautifyStat(String raw) {

        String processed = raw;

        switch (raw) {

            case "true":
                processed = "ja";
                break;
            case "false":
                processed = "nein";
                break;
            case "0":
            case "x":
                processed = "";
                break;
            //TODO FOR NOTES: can't have regex in case, but in default like this:
            default:
                //TODO: regex is totally wrong...
                if (raw.matches("^[123456789]F")) {
                    processed = beautifyRangeWeaponranges(raw);
                }
        }
        return processed;
    }

    private String beautifyRangeWeaponranges(String rawRanges) {
        //2F:16, 5F:13, 8F:9
        StringBuilder processed = new StringBuilder();
        String[] ranges = rawRanges.split("");

        for (String s : ranges) {
            String[] details = s.split(":");
            processed.append(details[0]).append(" Felder: ").append(details[1]).append(" ");
        }
        return processed.toString();
    }


}
