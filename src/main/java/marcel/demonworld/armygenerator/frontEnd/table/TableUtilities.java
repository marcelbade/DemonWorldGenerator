package marcel.demonworld.armygenerator.frontEnd.table;

import marcel.demonworld.armygenerator.dto.statCardDTOs.DemonWorldCard;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import marcel.demonworld.armygenerator.services.SelectArmyService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class TableUtilities {

    SelectArmyService service = new SelectArmyService();

    public List<String> getTableAttributeNames(Object dto) {

        List<String> names = new ArrayList<>();

        Arrays.stream(dto.getClass().getDeclaredFields()).forEach(f -> f.setAccessible(true));
        Arrays.stream(dto.getClass().getDeclaredFields()).forEach(f -> names.add(f.getName()));

        names.forEach(System.out::println);

        return names;
    }


    /**
     * Key method. This method remaps your repo DTO to a 2D-array that Swing uses for tables.
     * @returna 2D-array [unitcard ][values]
     * @throws IllegalAccessException
     */
    public String[][] remapData() throws IllegalAccessException {

        // array size through reflection
        String[][] result = new String[service.returnArmy("Zwerge").size()][UnitCard.class.getDeclaredFields().length];

        List<UnitCard> armyList = service.returnAll();
        List<String> oneCard = new ArrayList<>();

        Arrays.stream(armyList.getClass().getDeclaredFields()).forEach(f -> f.setAccessible(true));

        for (int i = 0; i < armyList.size(); i++) {

            UnitCard uc = armyList.get(i);

            for (int j = 0; j < uc.getClass().getDeclaredFields().length; j++) {

                Field f = uc.getClass().getDeclaredFields()[j];
                result[i][j] = (String) f.get(this);
            }
        }
        return result;
    }
}
