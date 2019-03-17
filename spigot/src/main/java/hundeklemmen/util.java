package hundeklemmen;


import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class util {
    public static void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch(Exception exc) {
            exc.printStackTrace();
        }
    }

    public static void checkVersion(){
        main.instance.getLogger().info("Checking for updates");
        try {
            String githubLatest = util.fireGet("https://api.github.com/repos/drupijs/Drupi-JS/releases/latest").toString();
            Map<String, Object> javaMap = new Gson().fromJson(githubLatest, Map.class);
            String latestVersion = javaMap.get("tag_name").toString();
            String currentVersion = main.instance.getDescription().getVersion();
            if(!latestVersion.equalsIgnoreCase(currentVersion)){
                main.instance.getLogger().info("Whoups! It looks like Drupi is out of date!");
                main.instance.getLogger().info("Pleaes go download the latest version of Drupi at https://www.spigotmc.org/resources/drupi-js.65706/");
                main.instance.getLogger().info("!NOTE! If you don't have access to updating Drupi, please contact those who have.");
                main.instance.update = true;
            } else {
                main.instance.getLogger().info("You're running the latest version of Drupi!");
            }
        } catch (IOException e) {
            main.instance.getLogger().info("Something went wrong while trying to check for updates!");
            e.printStackTrace();
        }

    }

    public static String fireGet(String urlParam) throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlParam);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    };
}
