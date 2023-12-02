package tech.nicecraftz.spigot.util;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;
import tech.nicecraftz.common.Messager;
import tech.nicecraftz.spigot.GrimBridge;

@UtilityClass
public class MessagingHelper {
    public void sendBungeeMessage(Player sender, String message) {
        ByteArrayDataOutput byteArrayOutputStream = ByteStreams.newDataOutput();
        byteArrayOutputStream.writeUTF(message);
        sender.sendPluginMessage(GrimBridge.getInstance(), Messager.PLUGIN_CHANNEL, byteArrayOutputStream.toByteArray());
    }

}
