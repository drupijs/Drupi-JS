package hundeklemmen.shared.api;

import hundeklemmen.shared.api.interfaces.ScriptLoadMessage;
import jdk.nashorn.api.scripting.NashornScriptEngine;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class DrupiScript {

    public File File;

    public DrupiScript(File File){
        this.File = File;
    }

    public void Load(Drupi Drupi, ScriptLoadMessage SLM){
        NashornScriptEngine engine = Drupi.engine;

        try (final Reader reader = new InputStreamReader(new FileInputStream(File), "UTF-8")) {
            engine.eval(reader);
            Drupi.log.info("Loaded Script: " + File.getName());
            SLM.onSuccess();
        } catch (final Exception e) {
            Drupi.log.warning("An error while loading " + File.getName());
            SLM.onError(e.getMessage().replaceAll("<eval>", File.getName()));
            e.printStackTrace();
        }
    }
}
