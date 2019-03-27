package hundeklemmen.script;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import hundeklemmen.MainPlugin;

import java.net.UnknownHostException;
import java.util.Map;

public class DatabaseManager {

    private MainPlugin plugin;

    public DatabaseManager(MainPlugin plugin){
        this.plugin = plugin;
    }

    public MongoClientURI newUrl(String url){
        return new MongoClientURI(url);
    }

    public MongoClient newClient(MongoClientURI url){
        try {
            return new MongoClient(url);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BasicDBObject newObj(){
        return new BasicDBObject();
    }

    public BasicDBObject convertJSON(Map<Object,Object> objectMap){
        BasicDBObject obj = new BasicDBObject();
        for(Map.Entry<Object, Object> entry : objectMap.entrySet()){
            obj.put(String.valueOf(entry.getKey()), entry.getValue());
        };
        return obj;
    }
}
