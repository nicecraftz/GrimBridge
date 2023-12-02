package tech.nicecraftz.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import lombok.Getter;
import org.slf4j.Logger;

@Plugin(
        id = "grimbridge",
        name = "GrimBridge",
        version = "1.0",
        authors = {"nicecraftz"}
)
@Getter
public class GrimBridge {
    private final Logger logger;
    private final ProxyServer proxyServer;
    private final VelocityMessager velocityMessager;

    @Inject
    public GrimBridge(Logger logger, ProxyServer proxyServer) {
        this.logger = logger;
        this.proxyServer = proxyServer;
        velocityMessager = new VelocityMessager(this);
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        velocityMessager.register();
        // Thanks to Kezz on the PaperMC who remembered me to put a listener else shit doesn't run for sure.
        proxyServer.getEventManager().register(this, velocityMessager);
        logger.info("GrimBridge has been enabled successfully!");
    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent proxyShutdownEvent) {
        velocityMessager.unregister();
        logger.info("GrimBridge has been disabled successfully!");
    }
}
