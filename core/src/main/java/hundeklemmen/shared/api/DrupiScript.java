package hundeklemmen.shared.api;

import hundeklemmen.shared.api.interfaces.ScriptLoadMessage;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import sun.misc.IOUtils;

import javax.script.Invocable;
import java.io.*;

public class DrupiScript {

    public File File;

    public DrupiScript(File File) {
        this.File = File;
    }

    public void Load(Drupi Drupi, NashornScriptEngine engine, Boolean useBabel, ScriptLoadMessage SLM) {
        String convertedScript = "";
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(File), "UTF-8"))) {
            //Loaded file
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;

            while ((line = br.readLine()) != null) {
                stringBuffer.append(line + System.lineSeparator());
            }
            if(stringBuffer.toString().contains("module.exports") && !this.File.getName().equalsIgnoreCase("utils.js")&& !this.File.getName().equalsIgnoreCase("babel.js")) return;
            if (useBabel == true && Drupi.config.compileMethod.equalsIgnoreCase("modern") || useBabel == true && Drupi.config.compileMethod.equalsIgnoreCase("legacy")) {
                //Time to run it through
                Invocable invocable = (Invocable) Drupi.engine;
                if(Drupi.config.compileMethod.equalsIgnoreCase("modern")){
                    invocable = (Invocable) Drupi.compileEngine;
                }
                convertedScript = (String) invocable.invokeFunction("convertBabelJS", stringBuffer.toString());
                Drupi.engine.eval(convertedScript);
                String filePath = File.getPath();
                String scriptsPath = Drupi.DataFolder.toString() + java.io.File.separator + "scripts" + java.io.File.separator;
                String finalPath = filePath.replace(scriptsPath, "");
                Drupi.log.info("Loaded Script (Babel): " + finalPath);
                SLM.onSuccess();
            } else {
                engine.eval(stringBuffer.toString());
                String filePath = File.getPath();
                String scriptsPath = Drupi.DataFolder.toString() + java.io.File.separator + "scripts" + java.io.File.separator;
                String finalPath = filePath.replace(scriptsPath, "");
                Drupi.log.info("Loaded Script: " + finalPath);
                SLM.onSuccess();
            }
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