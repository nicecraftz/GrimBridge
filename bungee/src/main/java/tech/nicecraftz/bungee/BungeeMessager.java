package tech.nicecraftz.bungee;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import tech.nicecraftz.common.Messager;

@RequiredArgsConstructor
public class BungeeMessager implements Messager, Listener {
    private final GrimBridge grimBridge;

    @EventHandler
    public void onPluginMessage(PluginMessageEvent event) {
        if (!event.getTag().equalsIgnoreCase(Messager.PLUGIN_CHANNEL)) return;

        ByteArrayDataInput in = ByteStreams.newDataInput(event.getData());
        String command = in.readUTF();
        execute(command);
    }

    @Override
    public void register() {
        grimBridge.getProxy().registerChannel(PLUGIN_CHANNEL);
    }

    @Override
    public void unregister() {
        grimBridge.getProxy().unregisterChannel(PLUGIN_CHANNEL);

    }

    @Override
    public void execute(String command) {
        grimBridge.getProxy().getPluginManager().dispatchCommand(grimBridge.getProxy().getConsole(), command);
    }
}
