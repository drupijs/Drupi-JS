package hundeklemmen;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.player.*;
import cn.nukkit.form.element.*;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.Item;
import cn.nukkit.level.Location;
import cn.nukkit.network.protocol.ProtocolInfo;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.AsyncTask;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.LoginChainData;
import cn.nukkit.utils.TextFormat;
import com.gitlab.johnjvester.randomizer.RandomGenerator;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.socket.client.Ack;
import io.socket.emitter.Emitter;
import org.bson.types.ObjectId;
import org.itxtech.synapseapi.SynapseAPI;
import org.itxtech.synapseapi.SynapseEntry;
import org.itxtech.synapseapi.SynapsePlayer;
import org.itxtech.synapseapi.utils.ClientData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.crypto.KeyGenerator;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class main extends PluginBase implements Listener {

    public static main instance;
    public static int serverProtocolVersion;
    public static int Servers = 0;
    public static int Players = 0;

    @Override
    public void onEnable() {
        instance = this;
        serverProtocolVersion = getVersion();
        socketClient.connectAPI();
        databaseClient.connectClient();

        this.getLogger().info("§a> §fEnabled");
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getPluginManager().registerEvents(new events(), this);

        this.getServer().getScheduler().scheduleRepeatingTask(this, new Task(){
            @Override
            public void onRun(int i) {
                updatePlayers();
            }
        }, 20*30);
    }
    public void updatePlayers(){
        socketClient.socket.emit("players", instance.getServer().getOnlinePlayers().size());

        instance.getServer().getScheduler().scheduleDelayedTask(instance, new Task(){
            @Override
            public void onRun(int i){
                socketClient.socket.emit("getPlayersAndServers", new Ack() {
                    @Override
                    public void call(Object... args) {
                        Players = Integer.parseInt(String.valueOf(args[0]));
                        Servers = Integer.parseInt(String.valueOf(args[1]));
                    }
                });
            }
        }, 40);
    }

    @Override
    public void onDisable() {
        this.getLogger().info("§a> §fDisabled");
    }

    private int getVersion(){
        try {
            Field f = ProtocolInfo.class.getDeclaredField("CURRENT_PROTOCOL");
            return f.getInt(null);
        } catch (Exception e) {
            this.getLogger().info("Failed to detect actual value of server protocol version. Will use precompiled value.");
            return ProtocolInfo.CURRENT_PROTOCOL;
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getLabel().equalsIgnoreCase("transfer")) {
            Player player = (Player) sender;
            player.transfer(new InetSocketAddress("ssh.hundeklemmen.com", 19133));
        } else if(command.getLabel().equalsIgnoreCase("server")){

            FormWindowSimple form = new FormWindowSimple(
                TextFormat.colorize("§eServer Menu"),
                TextFormat.colorize(""),
                new ArrayList<ElementButton>() {
                    {
                        add(new ElementButton("Join Server", new ElementButtonImageData("url", "http://www.nicurriculum.org.uk/STEMWorks/minecraft/images/viking-world-block.png")));
                        add(new ElementButton("Manage Your Server", new ElementButtonImageData("url", "http://aux2.iconspalace.com/uploads/3d-grass-minecraft-icon-256.png")));
                    }
                }
            );

            Player p = (Player) sender;
            p.showFormWindow(form, 0);
        } else if(command.getLabel().equalsIgnoreCase("join")){
            Player player = (Player) sender;
            if(args.length != 0) {
                String serverName = args[0].toLowerCase();
                main.instance.getServer().getScheduler().scheduleAsyncTask(main.instance, new AsyncTask() {
                    @Override
                    public void onRun() {
                        DBObject serverObject = databaseClient.servers.findOne(new BasicDBObject("name_lower", serverName));
                        if(serverObject != null) {
                            String status = (String) serverObject.get("status");
                            if(status.equalsIgnoreCase("offline")) {
                                socketClient.socket.emit("addToQueue", player.getName(), serverObject.get("_id").toString());
                                socketClient.socket.emit("controlServer", "start", serverObject.get("_id").toString(), new Ack() {
                                    @Override
                                    public void call(Object... args) {
                                        player.sendMessage("§8[§bPeHost§8] §a"+args[0].toString());
                                    }
                                });
                            } else {
                                String serverName = (String) serverObject.get("name");
                                String serverIP = "deamon" + serverObject.get("deamon") + ".pehost.xyz";
                                Integer serverPort = (Integer) serverObject.get("port");

                                player.sendMessage("§8[§bPeHost§8] §aSending you to "+serverName);

                                if(player.getName().equalsIgnoreCase("HundeklemmenMC")&&serverIP.contains("deamon1")){
                                    serverIP = "10.2.1.162";
                                }

                                InetSocketAddress connectionData = new InetSocketAddress(serverIP, serverPort);
                                player.transfer(connectionData);
                            }
                        } else {
                            player.sendMessage("§8[§bPeHost§8] §aWhoups, it seems like we couldn't find a server with that name!");
                        }
                    }
                });
            } else {
                player.sendMessage("§8[§bPeHost§8] §aWhoups, it looks like you forgot to specify a server. Try: /join (server name)");
            }
        }
        return true;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.setJoinMessage("§8[§a§l+§r§8] §7"+e.getPlayer().getName());
        main.instance.getServer().getScheduler().scheduleDelayedTask(main.instance, new AsyncTask() {
            @Override
            public void onRun() {
                e.getPlayer().teleport(new Location(183.5, 5, 120.5, 10, 5));
            }
        }, 10);
        main.instance.getServer().getScheduler().scheduleAsyncTask(main.instance, new AsyncTask() {
            @Override
            public void onRun() {
                Player player = e.getPlayer();

                DBObject found = databaseClient.players.findOne(new BasicDBObject("xuid", player.getLoginChainData().getXUID()));
                if(found==null){
                    DBObject loginData = new BasicDBObject();
                    LoginChainData loginChainData = player.getLoginChainData();
                    loginData.put("ClientId", loginChainData.getClientId());
                    loginData.put("ClientUUID", loginChainData.getClientUUID().toString());

                    loginData.put("DeviceIP", player.getAddress().replaceAll("/", "").split(":")[0]);
                    loginData.put("DeviceId", loginChainData.getDeviceId());
                    loginData.put("DeviceModel", loginChainData.getDeviceModel());
                    loginData.put("DeviceOS", loginChainData.getDeviceOS());
                    loginData.put("GameVersion", loginChainData.getGameVersion());
                    loginData.put("IdentityPublicKey", loginChainData.getIdentityPublicKey());
                    loginData.put("LanguageCode", loginChainData.getLanguageCode());
                    loginData.put("ServerAddress", loginChainData.getServerAddress());

                    BasicDBList deviceList = new BasicDBList();
                    deviceList.add(loginData);

                    DBObject obj = new BasicDBObject("uuid", player.getUniqueId().toString());
                    obj.put("name", player.getName());
                    obj.put("xuid", player.getLoginChainData().getXUID());
                    obj.put("rank", "player");
                    double balance = 0.0;
                    obj.put("balance", balance);
                    obj.put("deviceList", deviceList);
                    databaseClient.players.insert(obj);
                } else {
                    DBObject loginData = new BasicDBObject();
                    LoginChainData loginChainData = player.getLoginChainData();
                    loginData.put("ClientId", (int) loginChainData.getClientId());
                    loginData.put("ClientUUID", loginChainData.getClientUUID().toString());

                    loginData.put("DeviceIP", player.getAddress().replaceAll("/", "").split(":")[0]);
                    loginData.put("DeviceId", loginChainData.getDeviceId());
                    loginData.put("DeviceModel", loginChainData.getDeviceModel());
                    loginData.put("DeviceOS", loginChainData.getDeviceOS());
                    loginData.put("GameVersion", loginChainData.getGameVersion());
                    loginData.put("IdentityPublicKey", loginChainData.getIdentityPublicKey());
                    loginData.put("LanguageCode", loginChainData.getLanguageCode());
                    loginData.put("ServerAddress", loginChainData.getServerAddress());

                    BasicDBList deviceList = (BasicDBList) found.get("deviceList");
                    boolean containsDevice = false;

                    for (String key : deviceList.keySet()) {
                        DBObject thing = (DBObject) deviceList.get(key);
                        String DeviceId = (String) thing.get("DeviceId");
                        String PlayerDeviceID = (String) ((DBObject) loginData).get("DeviceId");
                        if(DeviceId.equalsIgnoreCase(PlayerDeviceID)){
                            containsDevice = true;
                        }
                    }
                    if(containsDevice == false) {
                        deviceList.add(loginData);
                    }

                    DBObject newObj = found;
                    newObj.put("name", player.getName());
                    newObj.put("deviceList", deviceList);

                    databaseClient.players.update(new BasicDBObject("xuid", player.getLoginChainData().getXUID()), new BasicDBObject("$set", newObj));
                }

            }
        });
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage("§8[§c§l-§r§8] §7"+e.getPlayer().getName());
    }


    @EventHandler
    public void playerChat(PlayerChatEvent event){
        event.setFormat("§8[§7Player§8] §a"+event.getPlayer().getName()+" §f"+event.getMessage());
    }

    @EventHandler
    public void hungerChange(PlayerFoodLevelChangeEvent event){
        event.setFoodLevel(event.getPlayer().getFoodData().getMaxLevel());
    }

    @EventHandler
    public void playerDamage(EntityDamageEvent event){
        event.setCancelled();
    }

    @EventHandler
    public void ree(PlayerServerSettingsRequestEvent e){
        List<Element> elements = new ArrayList<Element>();
        elements.add(new ElementLabel("§fWelcome to the PEHost Network, "+e.getPlayer().getName()+"! Customize your experience on our server below.\n\n"));

        List<String> languages = new ArrayList<String>();
        languages.add("English");
        //languages.add("Danish");
        elements.add(new ElementDropdown("Language", languages, 0));
        elements.add(new ElementLabel("\n"));
        elements.add(new ElementLabel("§fPrivacy \n"));
        elements.add(new ElementLabel("§fWe collect information about your device and your connection, so that we can improve your experience on our server. For example we may decide where to host our servers based on our players' locations around the world. If you have further questions about how we handle data, you can contact us on our discord."));

        FormWindowCustom form = new FormWindowCustom("PEHost Server Settings", elements);
        form.setIcon("https://cdn.cloudprotected.net/rPPmDHlLQ1/5e9d17e41f784ae361ada1d0817186f6/kbyvrqw1jdj0s87sqfezr4hpd.png");
        e.setSettings(100, form);
    }

    @EventHandler
    public void onRespone(PlayerFormRespondedEvent e){
        if (e.getWindow() instanceof FormWindowSimple) {
            Player p = e.getPlayer();
            FormWindowSimple simple = (FormWindowSimple) e.getWindow();
            Integer formID = e.getFormID();
            if(formID == 0) {
                String buttonName = simple.getResponse().getClickedButton().getText();
                if (buttonName == "Join Server") {
                    main.instance.getServer().getScheduler().scheduleAsyncTask(main.instance, new AsyncTask() {
                        @Override
                        public void onRun() {
                            FormWindowSimple form = new FormWindowSimple(
                                    TextFormat.colorize("Server List"),
                                    TextFormat.colorize("")
                            );
                            try (DBCursor cursor = databaseClient.servers.find(new BasicDBObject("status", "online"))) {
                                while (cursor.hasNext()) {
                                    DBObject found = cursor.next();
                                    main.instance.getLogger().info((String) found.get("name"));
                                    String name = (String) found.get("name");
                                    String owner = (String) found.get("owner");
                                    String image = (String) found.get("image");
                                    String description = (String) found.get("description");
                                    form.addButton(new ElementButton(name, new ElementButtonImageData(ElementButtonImageData.IMAGE_DATA_TYPE_URL, image)));
                                }
                            }
                            p.showFormWindow(form, 1);
                        }
                    });

                } else if (buttonName == "Manage Your Server") {
                    main.instance.getServer().getScheduler().scheduleAsyncTask(main.instance, new AsyncTask() {
                        @Override
                        public void onRun() {
                            FormWindowSimple form = new FormWindowSimple(
                                    TextFormat.colorize("Manage Your Server"),
                                    TextFormat.colorize("")
                            );
                            DBObject playerObject = databaseClient.players.findOne(new BasicDBObject("xuid", p.getLoginChainData().getXUID().toString()));
                            int serverAmount = 0;
                            String playerID = playerObject.get("_id").toString();
                            try (DBCursor cursor = databaseClient.servers.find(new BasicDBObject("owner", playerID))) {
                                while (cursor.hasNext()) {
                                    serverAmount++;
                                    DBObject found = cursor.next();
                                    String name = (String) found.get("name");
                                    String owner = (String) found.get("owner");
                                    String image = (String) found.get("image");
                                    String description = (String) found.get("description");
                                    form.addButton(new ElementButton(name, new ElementButtonImageData(ElementButtonImageData.IMAGE_DATA_TYPE_URL, image)));
                                }
                            }
                            if (serverAmount < 3) {
                                form.addButton(new ElementButton("Create a server"));
                            }
                            p.showFormWindow(form, 4);
                        }
                    });
                }
            } else if(formID == 1){
                main.instance.getServer().getScheduler().scheduleAsyncTask(main.instance, new AsyncTask() {
                    @Override
                    public void onRun() {
                        String buttonName = simple.getResponse().getClickedButton().getText();
                        DBObject serverObject = databaseClient.servers.findOne(new BasicDBObject("name", buttonName));
                        DBObject playerObject = databaseClient.players.findOne(new BasicDBObject("_id", new ObjectId((String) serverObject.get("owner"))));

                        if (serverObject != null && playerObject != null) {
                            FormWindowSimple form = new FormWindowSimple(
                                    TextFormat.colorize((String) serverObject.get("name")),
                                    TextFormat.colorize(
                                            ""
                                                    + "§bOwner: &a" + "\n" + (String) playerObject.get("name") + "\n"
                                                    + "\n" + "§bDescription: §a" + "\n"
                                                    + (String) serverObject.get("description") + "\n"
                                    )
                            );
                            form.addButton(new ElementButton("Join Server"));
                            p.showFormWindow(form, 3);

                            main.instance.getServer().getLogger().info(p.getLoginChainData().getXUID());
                        } else {
                            FormWindowSimple form = new FormWindowSimple(
                                    TextFormat.colorize("§cError"),
                                    TextFormat.colorize("§cSomething went wrong! Please try again.")
                            );
                            p.showFormWindow(form, 443);
                        }
                    }
                });
            } else if(formID == 3) {
                String serverName = simple.getTitle();
                main.instance.getServer().dispatchCommand(p, "join "+serverName);
            } else if(formID == 4) {
                String buttonName = simple.getResponse().getClickedButton().getText();
                if (buttonName.equalsIgnoreCase("Create a server")) {
                    List<Element> elements = new ArrayList<Element>();
                    elements.add(0, new ElementLabel("§fHello " + p.getName() + ", provide the following information to set up your free server.\n\n"));

                    elements.add(1, new ElementInput("Server Name", "What should your server be named?"));

                    elements.add(2, new ElementLabel("Note: NA servers are currently disabled."));
                    List<String> regions = new ArrayList<String>();
                    regions.add("Europe");
                    elements.add(3, new ElementDropdown("Region", regions, 0));

                    elements.add(new ElementLabel("§r   "));
                    elements.add(4, new ElementToggle("Whitelist", false));


                    List<String> gamemodes = new ArrayList<String>();
                    gamemodes.add("Survival");
                    gamemodes.add("Creative");
                    gamemodes.add("Adventure");
                    gamemodes.add("Spectator");
                    elements.add(5, new ElementDropdown("Gamemode", gamemodes, 0));

                    elements.add(6, new ElementLabel("§cMax players is currently disabled, please only do this directly from your server with /settings!"));
                    elements.add(7, new ElementSlider("Max Players", 10, 10, 1, 10));


                    elements.add(8, new ElementInput("Server Description", "What is your server about?"));

                    FormWindowCustom form = new FormWindowCustom("Create Server", elements);

                    p.showFormWindow(form, 5);
                } else {
                    //Edit server "stuff" here
                    DBObject serverObject = databaseClient.servers.findOne(new BasicDBObject("name", buttonName));
                    List<Element> elements = new ArrayList<Element>();
                    elements.add(0, new ElementLabel("§fHello " + p.getName() + ", here you can manage your server information.\n\n"));

                    elements.add(1, new ElementInput("Server Name", "What should your server be named?", (String) serverObject.get("name")));


                    int defualtOptionRegion = 0;
                    if(((String)serverObject.get("region")).equalsIgnoreCase("europe")){
                        defualtOptionRegion = 0;
                    }
                    elements.add(2, new ElementLabel("Note: NA servers are currently disabled."));
                    List<String> regions = new ArrayList<String>();
                    regions.add("Europe");
                    elements.add(3, new ElementDropdown("Region", regions, defualtOptionRegion));

                    elements.add(4, new ElementLabel("§r   "));

                    elements.add(5, new ElementLabel("§cMax players is currently disabled, please only do this directly from your server with /settings!"));
                    elements.add(6, new ElementSlider("Max Players", 10, 10, 1, 10));


                    elements.add(7, new ElementInput("Server Description", "What is your server about?", (String) serverObject.get("description")));

                    FormWindowCustom form = new FormWindowCustom(buttonName, elements);

                    p.showFormWindow(form, 6);
                }
            }
        } else if (e.getWindow() instanceof FormWindowCustom) {
            FormWindowCustom form = (FormWindowCustom) e.getWindow();
            Player player = e.getPlayer();
            if(e.getFormID() == 6) {
                String serverName = form.getResponse().getInputResponse(1);
                String region = form.getResponse().getDropdownResponse(3).getElementContent().toString();
                int maxPlayers = (int) form.getResponse().getSliderResponse(6);
                String description = form.getResponse().getInputResponse(7);

                DBObject serverObject = databaseClient.servers.findOne(new BasicDBObject("name", ((FormWindowCustom) e.getWindow()).getTitle()));

                if(serverName.equalsIgnoreCase(form.getTitle())){
                    DBObject newObj = serverObject;
                    newObj.put("region", region.toLowerCase());
                    newObj.put("maxPlayers", maxPlayers);
                    newObj.put("description", description);

                    databaseClient.servers.update(new BasicDBObject("name", form.getTitle()), new BasicDBObject("$set", newObj));
                } else {
                    DBObject checkServer = databaseClient.servers.findOne(new BasicDBObject("name_lower", serverName.toLowerCase()));
                    if (checkServer == null) {
                        DBObject newObj = serverObject;
                        newObj.put("name", serverName);
                        newObj.put("name_lower", serverName.toLowerCase());
                        newObj.put("region", region.toLowerCase());
                        newObj.put("maxPlayers", maxPlayers);
                        newObj.put("description", description);

                        databaseClient.servers.update(new BasicDBObject("name", form.getTitle()), new BasicDBObject("$set", newObj));
                    } else {
                        //Server name is taken EDIT
                        List<Element> elements2 = new ArrayList<Element>();
                        elements2.add(0, new ElementLabel("§fHello " + player.getName() + ", here you can manage your server information.\n\n"));

                        elements2.add(1, new ElementInput("Server Name", "What should your server be named?", (String) serverObject.get("name")));


                        int defualtOptionRegion = 0;
                        if(((String)serverObject.get("region")).equalsIgnoreCase("europe")){
                            defualtOptionRegion = 0;
                        }
                        elements2.add(2, new ElementLabel("Note: NA servers are currently disabled."));
                        List<String> regions2 = new ArrayList<String>();
                        regions2.add("Europe");
                        elements2.add(3, new ElementDropdown("Region", regions2, defualtOptionRegion));

                        elements2.add(4, new ElementLabel("§cMax players is currently disabled, please only do this directly from your server with /settings!"));

                        elements2.add(5, new ElementSlider("Max Players", 10, 10, 1, ((int) serverObject.get("maxPlayers"))));


                        elements2.add(6, new ElementInput("Server Description", "What is your server about?", (String) serverObject.get("description")));

                        FormWindowCustom form2 = new FormWindowCustom(form.getTitle(), elements2);

                        player.showFormWindow(form2, 6);
                    }
                }

            } else if(e.getFormID() == 5) {
                main.instance.getServer().getScheduler().scheduleAsyncTask(main.instance, new AsyncTask() {
                    @Override
                    public void onRun() {

                        String serverName = form.getResponse().getInputResponse(1);
                        String region = form.getResponse().getDropdownResponse(3).getElementContent().toString();
                        Boolean whitelist = form.getResponse().getToggleResponse(4);
                        String gameMode = form.getResponse().getDropdownResponse(5).getElementContent().toString();
                        int maxPlayers = (int) form.getResponse().getSliderResponse(7);
                        String description = form.getResponse().getInputResponse(8);

                        System.out.println("Name: " + serverName);
                        System.out.println("whitelist: " + whitelist);
                        System.out.println("gameMode: " + gameMode);
                        System.out.println("maxPlayers: " + maxPlayers);
                        System.out.println("description: " + description);

                        //check if name is taken.
                        DBObject checkServer = databaseClient.servers.findOne(new BasicDBObject("name_lower", serverName.toLowerCase()));
                        if (checkServer == null) {
                            DBObject playerObject = databaseClient.players.findOne(new BasicDBObject("xuid", player.getLoginChainData().getXUID().toString()));
                            int lastServerPort = 0;
                            try (DBCursor serversCursor = databaseClient.servers.find()) {
                                while (serversCursor.hasNext()) {
                                    DBObject next = serversCursor.next();
                                    lastServerPort = Integer.parseInt(String.valueOf(next.get("port")));
                                }
                            }
                            int serverPort = lastServerPort + 1;
                            String playerID = playerObject.get("_id").toString();

                            DBObject obj = new BasicDBObject();
                            obj.put("name", serverName);
                            obj.put("name_lower", serverName.toLowerCase());
                            obj.put("owner", playerID);
                            obj.put("port", serverPort);

                            obj.put("apikey", util.generateAPIKey());
                            obj.put("created", new Date().getTime());
                            obj.put("maxPlayers", maxPlayers);
                            obj.put("tier", 0);
                            obj.put("ram", 512);
                            obj.put("deamon", 1);
                            obj.put("status", "offline");
                            obj.put("starting", false);
                            obj.put("stopping", false);
                            obj.put("pid", 0);
                            obj.put("region", region.toLowerCase());
                            obj.put("description", description);
                            obj.put("image", "http://www.stickpng.com/assets/images/580b57fcd9996e24bc43c2f1.png");

                            DBObject setupObj = new BasicDBObject();
                            setupObj.put("whitelist", whitelist);
                            setupObj.put("gamemode", gameMode);

                            obj.put("setup", setupObj);
                            databaseClient.servers.insert(obj);
                            player.sendTitle("§aSuccess!", "§aYour server has now been created");

                            main.instance.getServer().getScheduler().scheduleDelayedTask(main.instance, new AsyncTask() {
                                @Override
                                public void onRun() {
                                    player.sendTitle("§aStarting server", "§aPlease wait while we're starting your server.");
                                    main.instance.getServer().dispatchCommand(player, "join "+serverName);
                                }
                            }, 100);
                        } else {
                            List<Element> elements = new ArrayList<Element>();
                            elements.add(0, new ElementLabel("§fHello " + player.getName() + ", provide the following information to set up your free server.\n§cServer name is taken, try another one.\n"));

                            elements.add(1, new ElementInput("Server Name", "What should your server be named?"));

                            elements.add(2, new ElementLabel("Note: NA servers are currently disabled."));
                            List<String> regions = new ArrayList<String>();
                            regions.add("Europe");
                            elements.add(3, new ElementDropdown("Region", regions, 0));

                            elements.add(new ElementLabel("§r   "));
                            elements.add(4, new ElementToggle("Whitelist", false));


                            List<String> gamemodes = new ArrayList<String>();
                            gamemodes.add("Survival");
                            gamemodes.add("Creative");
                            gamemodes.add("Adventure");
                            gamemodes.add("Spectator");
                            elements.add(5, new ElementDropdown("Gamemode", gamemodes, 0));

                            elements.add(6,new ElementLabel("§cMax players is currently disabled, please only do this directly from your server with /settings!"));
                            elements.add(7, new ElementSlider("Max Players", 10, 10, 1, 5));


                            elements.add(8, new ElementInput("Server Description", "What is your server about?"));

                            FormWindowCustom form = new FormWindowCustom("Create Server", elements);

                            player.showFormWindow(form, 5);
                        };
                    }
                });
            }

        }
    }
}
