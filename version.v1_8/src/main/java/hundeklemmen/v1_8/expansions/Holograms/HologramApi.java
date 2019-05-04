package hundeklemmen.v1_8.expansions.Holograms;

import com.sainttx.holograms.HologramPlugin;
import com.sainttx.holograms.api.Hologram;
import com.sainttx.holograms.api.HologramManager;
import com.sainttx.holograms.api.line.HologramLine;
import com.sainttx.holograms.api.line.ItemLine;
import com.sainttx.holograms.api.line.TextLine;
import com.sainttx.holograms.api.line.TextualHologramLine;
import com.sainttx.holograms.util.TextUtil;
import hundeklemmen.shared.api.Drupi;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class HologramApi {

    private Drupi drupi;
    private HologramManager hologramManager;

    public HologramApi(Drupi drupi){
        this.drupi = drupi;
        this.hologramManager = ((HologramPlugin) Bukkit.getPluginManager().getPlugin("Holograms")).getHologramManager();
    }

    public HologramManager HologramManager(){
        return this.hologramManager;
    }

    public void Create(String id, Location location){
        Hologram holo = new Hologram(id, location);
        hologramManager.addActiveHologram(holo);
    }

    public Hologram Get(String id){
        return hologramManager.getHologram(id);
    }

    public void Delete(Hologram hologram) {
        hologramManager.deleteHologram(hologram);
    }

    public void Move(Hologram hologram, Location location){
        hologram.teleport(location);
    }

    public void Hide(Hologram hologram) {
        hologram.despawn();
        hologramManager.removeActiveHologram(hologram);
    }
    public void Show(Hologram hologram) {
        hologram.spawn();
        hologramManager.addActiveHologram(hologram);
    }

    public boolean IsVisible(Hologram hologram){
        return hologramManager.getActiveHolograms().containsKey(hologram.getId());
    }

    public void AddLine(Hologram hologram, String text) {
        HologramLine line = new TextLine(hologram, text);
        hologram.addLine(line);
    }

    public void AddItemLine(Hologram hologram, ItemStack itemstack) {
        HologramLine line = new ItemLine(hologram, itemstack);
        hologram.addLine(line);
    }

    public void RemoveLine(Hologram hologram, int index){
        if (index > 0 || index <= hologram.getLines().size()) {
            HologramLine line = hologram.getLine(index);
            hologram.removeLine(line);

            if (hologram.getLines().size() == 0) {
                hologramManager.deleteHologram(hologram);
            } else {
                hologramManager.saveHologram(hologram);
            }
        }
    }

    public void SetLine(Hologram hologram, int index, String text){
        if (index > 0 || index <= hologram.getLines().size()) {
            HologramLine line = hologram.getLine(index);
            hologram.removeLine(line);

            if (line instanceof TextualHologramLine) {
                ((TextualHologramLine) line).setText(TextUtil.color(text));
                line.getHologram().setDirty(true);
                hologramManager.saveHologram(hologram);
            }
        }
    }

}
