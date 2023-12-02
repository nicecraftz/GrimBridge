package tech.nicecraftz.spigot.listener;

import ac.grim.grimac.events.CommandExecuteEvent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import tech.nicecraftz.spigot.util.MessagingHelper;

public class GrimAPIListener implements Listener {

    @EventHandler
    public void onCheat(CommandExecuteEvent event) {
        String command = event.getCommand();
        if (!command.startsWith("proxy:")) return;
        event.setCancelled(true);
        System.out.println("Handling proxy command");

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(event.getPlayer().getUniqueId());
        if (!offlinePlayer.isOnline()) return;
        Player player = offlinePlayer.getPlayer();
        MessagingHelper.sendBungeeMessage(player, command.replace("proxy:", ""));
        System.out.println("Sent proxy command to proxy.");
    }

}
