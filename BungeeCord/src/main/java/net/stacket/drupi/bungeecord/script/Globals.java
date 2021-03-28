package net.stacket.drupi.bungeecord.script;

import org.graalvm.polyglot.Value;

import net.md_5.bungee.api.scheduler.TaskScheduler;
import net.stacket.drupi.bungeecord.MainPlugin;

public class Globals {
    private final TaskScheduler scheduler = MainPlugin.instance.getProxy().getScheduler();

    public Globals(){}

    public int setTimeout(Value fn, int delay){
        return scheduler.schedule(MainPlugin.instance, () -> {
            if(fn.canExecute()) fn.executeVoid();
        }, delay, java.util.concurrent.TimeUnit.SECONDS).getId();
    }

    public int runAsync(Value fn, int delay){
      return scheduler.runAsync(MainPlugin.instance, () -> {
          if(fn.canExecute()) fn.executeVoid();
      }).getId();
    }


    public int setInterval(Value fn, int delay){
        return scheduler.schedule(MainPlugin.instance, () -> {
            if(fn.canExecute()) fn.executeVoid();
        }, 0, delay, java.util.concurrent.TimeUnit.SECONDS).getId();
    }


    public void clearInterval(int id){
        scheduler.cancel(id);
    }

    public void clearTimeout(int id){
        scheduler.cancel(id);
    }
}