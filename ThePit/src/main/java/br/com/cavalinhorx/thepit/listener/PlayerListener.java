package br.com.cavalinhorx.thepit.listener;

import br.com.cavalinhorx.thepit.player.TPlayer;
import br.com.cavalinhorx.thepit.player.TPlayerCRUD;
import br.com.cavalinhorx.thepit.player.TPlayerCache;
import br.com.cavalinhorx.thepit.sidebar.TPScoreboard;
import br.com.cavalinhorx.thepit.util.AnyUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    @EventHandler
    void preLoginEvent(AsyncPlayerPreLoginEvent event) {
        TPlayerCRUD tPlayerCRUD = new TPlayerCRUD();

        if (tPlayerCRUD.read(event.getUniqueId()) != null)
            TPlayerCache.getInstance().add(event.getUniqueId(), tPlayerCRUD.read(event.getUniqueId()));
    }

    @EventHandler
    void playerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!TPlayerCache.getInstance().contains(player.getUniqueId()))
            player.kickPlayer(ChatColor.RED + "Moto" + ChatColor.YELLOW + "Crack\n" + ChatColor.GREEN + "Ocorreu um erro ao buscar suas informações\n" + ChatColor.GREEN + "Contate algum staff.");

        TPlayer tPlayer = TPlayerCache.getInstance().retriever(player.getUniqueId());

        player.setLevel(tPlayer.getLevel());
        player.setExp(tPlayer.getXp());

        new TPScoreboard(player).show();
    }

    @EventHandler
    void playerDamageEvent(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    void playerDamageByPlayerEvent(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getDamager() instanceof Player) {
                Player damaged = (Player) event.getDamager();
                AnyUtil.sendActionBar(damaged, AnyUtil.progressBar(Math.round(player.getHealth()), 20, 20, "♥", "§c", "§7"));
            }
        }
    }

    @EventHandler
    void playerDeathEvent(PlayerDeathEvent event) {
        Player player = event.getEntity().getKiller();
        TPlayer tPlayer = TPlayerCache.getInstance().retriever(player.getUniqueId());

        player.setExp(+20);

        tPlayer.setGold(+70);
        tPlayer.setXp(Math.round(player.getExp()));
        tPlayer.setLevel(Math.round(player.getLevel()));
        tPlayer.getTpScoreboard().update();
    }
}