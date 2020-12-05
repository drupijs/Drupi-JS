package hundeklemmen.shared.api;

import hundeklemmen.shared.api.interfaces.ScriptLoadMessage;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import java.io.*;

public class DrupiScript {

    public File File;

    public DrupiScript(File File) {
        this.File = File;
    }

    public void Load(Drupi Drupi, ScriptEngine engine, Boolean useBabel, ScriptLoadMessage SLM) {
        String convertedScript = "";
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(File), "UTF-8"))) {
            //Loaded file
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;

            while ((line = br.readLine()) != null) {
                stringBuffer.append(line + System.lineSeparator());
            }
            boolean isModule = stringBuffer.toString().contains("module.exports") || stringBuffer.toString().contains("exports");
            if(stringBuffer.toString().startsWith("\"skip babel\";") == true || stringBuffer.toString().startsWith("'skip babel';") == true){
                useBabel = false;
            }
            if(isModule && !this.File.getName().equalsIgnoreCase("utils.js") && !this.File.getName().equalsIgnoreCase("babel.js")) return;

            engine.eval(stringBuffer.toString());
            String filePath = File.getPath();
            String scriptsPath = Drupi.DataFolder.toString() + java.io.File.separator + "scripts" + java.io.File.separator;
            String finalPath = filePath.replace(scriptsPath, "");
            Drupi.log.info("Loaded Script: " + finalPath);
            SLM.onSuccess();

        } catch (final Exception e) {
            String filePath = File.getPath();
            String scriptsPath = Drupi.DataFolder.toString() + java.io.File.separator + "scripts" + java.io.File.separator;
            String finalPath = filePath.replace(scriptsPath, "");
            Drupi.log.warning("An error while loading " + finalPath);
            SLM.onError(e.getMessage().replaceAll("<eval>", finalPath));
            e.printStackTrace();
        }
    }
}