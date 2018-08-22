package br.com.cavalinhorx.thepit.util;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.apache.commons.lang.StringUtils;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class AnyUtil {

    public static void sendActionBar(Player player, String message) {
        PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}"), (byte)2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutChat);
    }

    public static String progressBar(long current, long max, int totalBars, String barChar, String complete, String noComplete){
        float percent = (float) current / max;
        int progressBars = (int) (totalBars * percent);

        if(current >= max) {
            return complete;
        }

        return StringUtils.repeat(complete + barChar, progressBars) + StringUtils.repeat(noComplete + barChar, totalBars - progressBars);
    }
}