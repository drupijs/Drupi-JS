package hundeklemmen.minehut.script;

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
}
