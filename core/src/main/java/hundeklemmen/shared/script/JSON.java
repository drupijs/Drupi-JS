package hundeklemmen.shared.script;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JSON {

    public String stringify(Map<Object,Object> objectMap){
        JSONObject obj = new JSONObject();
        for(Map.Entry<Object, Object> entry : objectMap.entrySet()){
            obj.put(String.valueOf(entry.getKey()), entry.getValue());
        };
        return obj.toString();
    }
    public Map<String, Object> parse(String raw){
        try {
            Map<String, Object> objectMap = new HashMap<String, Object>();
            JSONObject obj = new JSONObject(raw);
            obj.keySet().forEach(keyStr -> {
                Object keyvalue = obj.get(keyStr);
                objectMap.put(keyStr, keyvalue);
            });
            return objectMap;
        } catch(org.json.JSONException e){
            return null;
        }
    }
    public static JSONObject toJson(Map<Object, Object> objectMap){
        JSONObject obj = new JSONObject();
        for(Map.Entry<Object, Object> entry : objectMap.entrySet()){
            obj.put(String.valueOf(entry.getKey()), entry.getValue());
        };
        return obj;
    }


}
