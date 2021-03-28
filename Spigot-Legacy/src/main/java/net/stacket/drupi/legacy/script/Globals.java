package net.stacket.drupi.legacy.script;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.graalvm.polyglot.Value;

import net.stacket.drupi.legacy.MainPlugin;

public class Globals {

    private final BukkitScheduler scheduler = MainPlugin.instance.getServer().getScheduler();

    public Globals(){}

    public int setTimeout(Value fn, int delay){
        return scheduler.scheduleSyncDelayedTask(MainPlugin.instance, () -> {
            if(fn.canExecute()) fn.executeVoid();
        }, delay);
    }

    public int setInterval(Value fn, int delay){
        return scheduler.scheduleSyncRepeatingTask(MainPlugin.instance, () -> {
            if(fn.canExecute()) fn.executeVoid();
        }, 0, delay);
    }

    public int setAsyncTimeout(Value fn, int delay){
        BukkitTask id = new BukkitRunnable() {
            public void run() {
                if(fn.canExecute()) fn.executeVoid();
            }
        }.runTaskLaterAsynchronously(MainPlugin.instance, delay);
        return id.getTaskId();
    }

    public int setAsyncInterval(Value fn, int delay){
        BukkitTask id = new BukkitRunnable() {
            public void run() {
                if(fn.canExecute()) fn.executeVoid();
            }
        }.runTaskTimerAsynchronously(MainPlugin.instance, 0, delay);
        return id.getTaskId();
    }

    public void clearInterval(int id){
        scheduler.cancelTask(id);
    }

    public void clearTimeout(int id){
        scheduler.cancelTask(id);
    }

}