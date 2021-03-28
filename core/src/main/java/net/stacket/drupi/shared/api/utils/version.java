package net.stacket.drupi.shared.api.utils;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

public class version {

    public boolean updateAvailable(String current){
        try {
            String githubLatest = http.fireGet("https://api.github.com/repos/drupijs/Drupi-JS/releases/latest").toString();
            Map<String, Object> javaMap = new Gson().fromJson(githubLatest, Map.class);
            String latestVersion = javaMap.get("tag_name").toString();
            if(latestVersion.equalsIgnoreCase(current)){
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }
}
