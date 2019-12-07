package hundeklemmen.legacy.script;

import hundeklemmen.shared.script.CastManager;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.material.Wool;

public class castManager extends CastManager {

    public Player asPlayer(Object sender){
        return (Player) sender;
    }
    public Wool asWool(Object obj){
        return (Wool) obj;
    }
    public Sign asSign(Object obj){
        return (Sign) obj;
    }
    //public int getPing(Player p) {
    //    CraftPlayer cp = (CraftPlayer) p;
    //    EntityPlayer ep = cp.getHandle();
    //    return ep.ping;
    //}
    public Object as(String type, Object obj){
        try {
            return Class.forName(type).cast(obj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
            //(CraftPlayer) obj
        }
    }
}
