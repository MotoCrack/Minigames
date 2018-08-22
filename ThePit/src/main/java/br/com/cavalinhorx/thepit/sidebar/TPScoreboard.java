package br.com.cavalinhorx.thepit.sidebar;

import br.com.cavalinhorx.thepit.player.TPlayer;
import br.com.cavalinhorx.thepit.player.TPlayerCache;
import br.com.cavalinhorx.thepit.util.Sidebar;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TPScoreboard {

    private Sidebar sidebar;
    private Player player;

    public TPScoreboard(Player player) {
        this.player = player;
    }

    public void show() {
        sidebar = new Sidebar(ChatColor.RED + "The " + ChatColor.YELLOW + "Pit");

        TPlayer tPlayer = TPlayerCache.getInstance().retriever(player);

        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        sidebar.add(ChatColor.GRAY + zonedDateTime.format(dateTimeFormatter), 8);
        sidebar.add("", 7);
        sidebar.add("Nível: " + ChatColor.GRAY + "[" + ChatColor.GREEN + tPlayer.getLevel() + ChatColor.GRAY + "]", 6);
        sidebar.add("Próximo nível: " + ChatColor.AQUA + tPlayer.getXp(), 5);
        sidebar.add("", 4);
        sidebar.add("Ouro" + ChatColor.GOLD + tPlayer.getGold(), 3);
        sidebar.add("", 2);
        sidebar.add(ChatColor.YELLOW + "www.motocrack.net", 1);

        sidebar.send(player);
    }

    public void update() {
        sidebar.update();
    }

}