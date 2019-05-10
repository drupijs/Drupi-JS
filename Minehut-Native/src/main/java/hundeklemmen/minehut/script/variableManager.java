package hundeklemmen.minehut.script;

import hundeklemmen.minehut.MainPlugin;
import org.bukkit.OfflinePlayer;

public class variableManager {

    private MainPlugin plugin;

    public variableManager(MainPlugin plugin) {
        this.plugin = plugin;
    }
    public Object get(String index) {
        if (MainPlugin.variables.containsKey(index)) {
            String type = MainPlugin.variables.get(index).getClass().getName();
            if (type.equals("cn.nukkit.OfflinePlayer") && ((OfflinePlayer) MainPlugin.variables.get(index)).getPlayer() != null) {
                return ((OfflinePlayer) MainPlugin.variables.get(index)).getPlayer();
            } else {
                return MainPlugin.variables.get(index);
            }
        } else {
            return null;
        }
    }

    public void set(String index, Object value) {
        if (MainPlugin.variables.containsKey(index)) {
            MainPlugin.variables.remove(index);
        }
        MainPlugin.variables.put(index, value);
    }

    public boolean exist(String index){
        if (MainPlugin.variables.containsKey(index)) {
            return true;
        } else {
            return false;
        }
    }

    public void delete(String index){
        if(MainPlugin.variables.containsKey(index)){
            MainPlugin.variables.remove(index);
        }
    }
}