package tech.nicecraftz.velocity;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.ChannelRegistrar;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import org.slf4j.Logger;
import tech.nicecraftz.common.Messager;

public class VelocityMessager implements Messager {
    private static final MinecraftChannelIdentifier MESSAGE_CHANNEL = MinecraftChannelIdentifier.from(Messager.PLUGIN_CHANNEL);
    private final Logger logger;
    private final ProxyServer proxyServer;
    private final ChannelRegistrar channelRegistrar;

    public VelocityMessager(GrimBridge grimBridge) {
        logger = grimBridge.getLogger();
        proxyServer = grimBridge.getProxyServer();
        channelRegistrar = proxyServer.getChannelRegistrar();
    }

    @Override
    public void register() {
        channelRegistrar.register(MESSAGE_CHANNEL);
    }

    @Override
    public void unregister() {
        channelRegistrar.unregister(MESSAGE_CHANNEL);
    }

    @Override
    public void execute(String command) {
        proxyServer.getCommandManager().executeAsync(proxyServer.getConsoleCommandSource(), command);
    }

    @Subscribe
    public void onPluginMessage(PluginMessageEvent event) {
        if (event.getIdentifier() != MESSAGE_CHANNEL) return;
        // Only accept servers
        if (event.getSource() instanceof Player) return;
        logger.info("Received punishment command from backend server, handling...");
        ByteArrayDataInput in = ByteStreams.newDataInput(event.getData());
        String command = in.readUTF();
        execute(command);
    }
}
