package tech.nicecraftz.bungee;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import tech.nicecraftz.common.Messager;

import java.util.logging.Logger;

public class BungeeMessager implements Messager, Listener {
    private final ProxyServer proxyServer;
    private final Logger logger;

    public BungeeMessager(GrimBridge grimBridge) {
        proxyServer = grimBridge.getProxy();
        logger = grimBridge.getLogger();
    }

    @EventHandler
    public void onPluginMessage(PluginMessageEvent event) {
        if (!event.getTag().equalsIgnoreCase(Messager.PLUGIN_CHANNEL)) return;
        logger.info("Received punishment command from backend server, handling...");

        ByteArrayDataInput in = ByteStreams.newDataInput(event.getData());
        String command = in.readUTF();
        execute(command);
    }

    @Override
    public void register() {
        proxyServer.registerChannel(PLUGIN_CHANNEL);
    }

    @Override
    public void unregister() {
        proxyServer.unregisterChannel(PLUGIN_CHANNEL);
    }

    @Override
    public void execute(String command) {
        proxyServer.getPluginManager().dispatchCommand(proxyServer.getConsole(), command);
    }
}
