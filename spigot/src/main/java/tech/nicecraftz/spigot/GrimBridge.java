package tech.nicecraftz.spigot;

import ac.grim.grimac.GrimAbstractAPI;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import tech.nicecraftz.common.Messager;
import tech.nicecraftz.spigot.listener.GrimAPIListener;

@Getter
public final class GrimBridge extends JavaPlugin {
    @Getter
    private static GrimBridge instance;
    private final PluginManager pluginManager = getServer().getPluginManager();
    private GrimAbstractAPI grimAbstractAPI;

    @Override
    public void onEnable() {
        instance = this;

        Plugin grimAcPlugin = pluginManager.getPlugin("GrimAC");
        if (grimAcPlugin == null || !grimAcPlugin.isEnabled()) {
            getLogger().severe("Couldn't load the plugin because Grim Anticheat is missing.");
            return;
        }

        RegisteredServiceProvider<GrimAbstractAPI> rsp = Bukkit.getServicesManager().getRegistration(GrimAbstractAPI.class);
        if (rsp == null) {
            getLogger().severe("Couldn't load the plugin because Grim Anticheat is missing.");
            return;
        }

        grimAbstractAPI = rsp.getProvider();
        getServer().getMessenger().registerOutgoingPluginChannel(this, Messager.PLUGIN_CHANNEL);
        pluginManager.registerEvents(new GrimAPIListener(), this);
    }

    @Override
    public void onDisable() {
        getServer().getMessenger().unregisterOutgoingPluginChannel(this);
    }
}
