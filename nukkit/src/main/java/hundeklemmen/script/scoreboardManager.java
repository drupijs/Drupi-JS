package hundeklemmen.script;

import cn.nukkit.Player;
import hundeklemmen.extra.scoreboard.network.DisplaySlot;
import hundeklemmen.extra.scoreboard.network.Scoreboard;
import hundeklemmen.extra.scoreboard.network.ScoreboardDisplay;
import hundeklemmen.main;

import java.util.HashMap;
import java.util.Map;

public class scoreboardManager {

    private main plugin;
    private static Map<Player, Scoreboard> scoreboards = new HashMap<>();

    public scoreboardManager(main plugin) {
        this.plugin = plugin;
    }

    public Scoreboard create(){
        return new Scoreboard();
    }
    public ScoreboardDisplay newDisplay(Scoreboard sc, String title){
        return sc.addDisplay(DisplaySlot.SIDEBAR, "dummy", title);
    }
    public void setLine(ScoreboardDisplay dis, String text, int numb){
        dis.addLine(text, numb);
    }
    public void set(Player player, Scoreboard scoreboard){
        if(scoreboards.containsKey(player)){
            scoreboards.get(player).hideFor(player);
            scoreboards.remove(player);
            scoreboards.put(player, scoreboard);
            scoreboard.showFor(player);
        } else {
            scoreboards.put(player, scoreboard);
            scoreboard.showFor(player);
        }
    }
    public void remove(Player player){
        if(scoreboards.containsKey(player)) {
            scoreboards.get(player).hideFor(player);
            scoreboards.remove(player);
        }
    }
}
