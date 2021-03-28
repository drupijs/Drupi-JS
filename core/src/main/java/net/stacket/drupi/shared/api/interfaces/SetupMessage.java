package net.stacket.drupi.shared.api.interfaces;

import org.graalvm.polyglot.Context;
public interface SetupMessage {

    void onMessage(String message);
    void loadManagers(Context engine);
}
