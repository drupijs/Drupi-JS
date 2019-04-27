package hundeklemmen.shared.api.interfaces;

import jdk.nashorn.api.scripting.NashornScriptEngine;

public interface SetupMessage {

    void onMessage(String message);
    void loadManagers(NashornScriptEngine engine);
}
