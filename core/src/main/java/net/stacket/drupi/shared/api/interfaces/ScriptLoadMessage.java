package net.stacket.drupi.shared.api.interfaces;

public interface ScriptLoadMessage {
    void onSuccess();
    void onError(String ScriptError);
}
