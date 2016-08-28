package me.itzmarcus.pingtracker.events;

import me.itzmarcus.pingtracker.Core;
import me.itzmarcus.pingtracker.commands.ActivateCommand;
import me.itzmarcus.pingtracker.utils.TitleManagerAPI;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by marcus on 28-08-2016.
 */
public class JoinEvent implements Listener {

    Core plugin;
    public JoinEvent(Core instance) {
        plugin = instance;
    }
    TitleManagerAPI titleManagerAPI = new TitleManagerAPI();

    @EventHandler
    public void join(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(ActivateCommand.activated.contains(p.getName())) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if(ActivateCommand.activated.contains(p.getName())) {
                        int ping = ((CraftPlayer) p).getHandle().ping;
                        String color = "§a";
                        if(ping >= 250) {
                            color = "§c";
                        } else if(ping >= 100) {
                            color = "§e";
                        } else if(ping <= 100) {
                            color = "§a";
                        }
                        String output = "" + color + ping + "ms";
                        titleManagerAPI.sendActionbarMessage(p, "§c" + p.getName() + "'s §fping: " + output);
                    } else {
                        cancel();
                    }
                }
            }.runTaskTimer(plugin, 0, 20);
        }
    }
}
