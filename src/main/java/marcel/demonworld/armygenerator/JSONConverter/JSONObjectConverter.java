package marcel.demonworld.armygenerator.JSONConverter;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import jakarta.persistence.*;


@Converter(autoApply = true)
public class JSONObjectConverter implements AttributeConverter<JSONObject, String> {


    @Override
    public String convertToDatabaseColumn(JSONObject obj) {
        String data = null;
        try {
            data = obj.toString();
        } catch (final Exception e) {
            System.out.println("JSON writing error");
        }
        return data;
    }

    @Override
    public JSONObject convertToEntityAttribute(String data) {
        JSONObject obj = null;

        try {
            Object temp = JSONValue.parse(data);
            obj = (JSONObject) temp;
        } catch (final Exception e) {
            System.out.println("JSON reading error");
        }
        return obj;
    }
}