package tech.nicecraftz.velocity;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import lombok.RequiredArgsConstructor;
import tech.nicecraftz.common.Messager;

@RequiredArgsConstructor
public class VelocityMessager implements Messager {
    private static final MinecraftChannelIdentifier MESSAGE_CHANNEL = MinecraftChannelIdentifier.from(Messager.PLUGIN_CHANNEL);
    private final ProxyServer proxyServer;

    @Override
    public void register() {
        proxyServer.getChannelRegistrar().register(MESSAGE_CHANNEL);
    }

    @Override
    public void unregister() {
        proxyServer.getChannelRegistrar().unregister(MESSAGE_CHANNEL);
    }

    @Override
    public void execute(String command) {
        proxyServer.getCommandManager().executeAsync(proxyServer.getConsoleCommandSource(), command);
    }

    @Subscribe
    public void onPluginMessage(PluginMessageEvent event) {
        System.out.println("Received proxy message");
        if (!(event.getSource() instanceof Player)) return;
        System.out.println("Source is player");
        if (event.getIdentifier() != MESSAGE_CHANNEL) return;
        System.out.println("identifier is right channel");

        ByteArrayDataInput in = ByteStreams.newDataInput(event.getData());
        String command = in.readUTF();
        System.out.println("Trying to execute " + command);
        execute(command);
        System.out.println("Ran command");
    }
}
