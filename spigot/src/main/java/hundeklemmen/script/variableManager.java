package hundeklemmen.script;

import hundeklemmen.main;
import org.bukkit.OfflinePlayer;

public class variableManager {

    private main plugin;

    public variableManager(main plugin) {
        this.plugin = plugin;
    }
    public Object get(String index) {
        if (main.variables.containsKey(index)) {
            String type = main.variables.get(index).getClass().getName();
            if (type.equals("cn.nukkit.OfflinePlayer") && ((OfflinePlayer) main.variables.get(index)).getPlayer() != null) {
                return ((OfflinePlayer) main.variables.get(index)).getPlayer();
            } else {
                return main.variables.get(index);
            }
        } else {
            return null;
        }
    }

    public void set(String index, Object value) {
        if (main.variables.containsKey(index)) {
            main.variables.remove(index);
        }
        main.variables.put(index, value);
    }

    public boolean exist(String index){
        if (main.variables.containsKey(index)) {
            return true;
        } else {
            return false;
        }
    }

    public void delete(String index){
        if(main.variables.containsKey(index)){
            main.variables.remove(index);
        }
    }
}