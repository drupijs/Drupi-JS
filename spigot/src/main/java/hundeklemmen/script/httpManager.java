package hundeklemmen.script;

import hundeklemmen.main;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class httpManager {
    private main plugin;
    public httpManager(main plugin){
        this.plugin = plugin;
    }

    public String get(String s){
        try {
            URL url = new URL(s);
            Scanner a = new Scanner(url.openStream());
            String str = "";
            boolean first = true;
            while(a.hasNext()){
                if(first) str = a.next();
                else str += " " + a.next();
                first = false;
            }
            return str;
        } catch(IOException ex) {
            return null;
        }
    }
}
