package tech.nicecraftz.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

@Plugin(
        id = "grimbridge",
        name = "GrimBridge",
        version = "1.0",
        authors = {"nicecraftz"}
)
public class GrimBridge {
    private final Logger logger;
    private final VelocityMessager velocityMessager;

    @Inject
    public GrimBridge(Logger logger, ProxyServer proxyServer) {
        this.logger = logger;
        velocityMessager = new VelocityMessager(proxyServer);
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        velocityMessager.register();
        logger.info("GrimBridge has been enabled successfully!");
    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent proxyShutdownEvent) {
        velocityMessager.unregister();
        logger.info("GrimBridge has been disabled successfully!");
    }
}
