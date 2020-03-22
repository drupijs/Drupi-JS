package hundeklemmen.bungeecord.script;

import hundeklemmen.bungeecord.MainPlugin;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.engineio.client.transports.WebSocket;

import java.net.URISyntaxException;

import static io.socket.client.IO.socket;


public class socketManager {

    private MainPlugin plugin;

    public socketManager(MainPlugin plugin){
        this.plugin = plugin;
    }

    public Socket newConnection(String url){
        return newConnection(url, true, true, true);
    }

    public Socket newConnection(String url, boolean websocketOnly, boolean forceNew, boolean reconnection){

        try {
            IO.Options opt = new IO.Options();
            if(websocketOnly == true){
                opt.transports = new String[]{WebSocket.NAME};
            }
            opt.forceNew = forceNew;
            opt.reconnection = reconnection;

            Socket sock = socket(url, opt);
            plugin.sockets.add(sock);
            return sock;


        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

}
