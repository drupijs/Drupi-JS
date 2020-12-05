package hundeklemmen.shared.api.interfaces;

import javax.script.ScriptEngine;

public interface SetupMessage {

    void onMessage(String message);
    void loadManagers(ScriptEngine engine);
}
