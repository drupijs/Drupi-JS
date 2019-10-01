package hundeklemmen.shared.script;

import com.google.gson.JsonObject;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JSON {

    public JSON(){ }

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
    public JSONObject toJson(Map<Object, Object> objectMap){
        JSONObject obj = new JSONObject();
        for(Map.Entry<Object, Object> entry : objectMap.entrySet()){
            obj.put(String.valueOf(entry.getKey()), entry.getValue());
        };
        return obj;
    }
    public JsonObject toGson(Map<Object, Object> objectMap){
        JsonObject obj = new JsonObject();
        for(Map.Entry<Object, Object> entry : objectMap.entrySet()){
            if(entry.getValue() instanceof String){
                obj.addProperty(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            } else if(entry.getValue() instanceof Number){
                obj.addProperty(String.valueOf(entry.getKey()), (Number) entry.getValue());
            } else if(entry.getValue() instanceof Boolean){
                obj.addProperty(String.valueOf(entry.getKey()), (boolean) entry.getValue());
            } else if(entry.getValue() instanceof Character){
                obj.addProperty(String.valueOf(entry.getKey()), (Character) entry.getValue());
            } else {
                obj.addProperty(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
        };
        return obj;
    }

    public JSONObject parseRawObject(String raw){
        return new JSONObject(raw);
    }
    public JSONArray parseRawArray(String raw){
        return new JSONArray(raw);
    }


}
