package hundeklemmen.minehut.expansions;

import hundeklemmen.minehut.MainPlugin;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;

import static hundeklemmen.minehut.MainPlugin.instance;

public class Vault {

    public Vault(){}

    public Economy economy(){
        RegisteredServiceProvider<Economy> rspE = instance.getServer().getServicesManager().getRegistration(Economy.class);
        if (rspE != null) {
            return rspE.getProvider();
        }
        return null;
    }

    public Permission permission(){
        RegisteredServiceProvider<Permission> rspP = MainPlugin.instance.getServer().getServicesManager().getRegistration(Permission.class);
        if (rspP != null) {
            return rspP.getProvider();
        }
        return null;
    }

    public Chat chat(){
        RegisteredServiceProvider<Chat> rspC = MainPlugin.instance.getServer().getServicesManager().getRegistration(Chat.class);
        if (rspC != null) {
            return rspC.getProvider();
        }
        return null;
    }
}
