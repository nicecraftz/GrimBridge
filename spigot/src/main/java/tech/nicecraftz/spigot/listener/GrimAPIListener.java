package tech.nicecraftz.spigot.listener;

import ac.grim.grimac.api.events.CommandExecuteEvent;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import tech.nicecraftz.spigot.util.MessagingHelper;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class GrimAPIListener implements Listener {
    private final Cache<UUID, Boolean> cache = CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.SECONDS).build();

    @EventHandler
    public void onCheat(CommandExecuteEvent event) {
        String command = event.getCommand();
        if (!command.startsWith("proxy:")) return;
        event.setCancelled(true);

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(event.getPlayer().getUniqueId());
        if (!offlinePlayer.isOnline()) return;

        Player player = offlinePlayer.getPlayer();
        if (cache.asMap().containsKey(player.getUniqueId())) return;
        MessagingHelper.sendBungeeMessage(player, command.replace("proxy:", ""));
        cache.put(player.getUniqueId(), true);
    }

}
