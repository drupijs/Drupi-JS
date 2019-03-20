package hundeklemmen.events;

import hundeklemmen.main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class weatherEvents implements Listener {

    @EventHandler
    public void LightningStrike(LightningStrikeEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void ThunderChange(ThunderChangeEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void WeatherChange(WeatherChangeEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
}
