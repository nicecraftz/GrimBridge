package tech.nicecraftz.common;

public interface Messager {
    String PLUGIN_CHANNEL = "grimbridge:communication";

    void register();

    void unregister();

    void execute(String command);
}
