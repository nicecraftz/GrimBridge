package tech.nicecraftz.common;

public interface Messager {
    String PLUGIN_CHANNEL = "grimbridge:msg";

    void register();

    void unregister();

    void execute(String command);
}
