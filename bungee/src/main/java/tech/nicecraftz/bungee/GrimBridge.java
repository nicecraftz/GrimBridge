package tech.nicecraftz.bungee;

import net.md_5.bungee.api.plugin.Plugin;
import tech.nicecraftz.common.Messager;

public final class GrimBridge extends Plugin implements Messager {
    private BungeeMessager bungeeMessager;

    @Override
    public void onEnable() {
        bungeeMessager = new BungeeMessager(this);
        bungeeMessager.register();
        getProxy().getPluginManager().registerListener(this, bungeeMessager);
    }

    @Override
    public void onDisable() {
        bungeeMessager.unregister();
    }

    @Override
    public void register() {
        getProxy().registerChannel(PLUGIN_CHANNEL);
    }

    @Override
    public void unregister() {
        getProxy().unregisterChannel(PLUGIN_CHANNEL);
    }

    @Override
    public void execute(String command) {
        getProxy().getPluginManager().dispatchCommand(getProxy().getConsole(), command);
    }
}
