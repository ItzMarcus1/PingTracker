package me.itzmarcus.pingtracker.commands;

import me.itzmarcus.pingtracker.Core;
import me.itzmarcus.pingtracker.utils.TitleManagerAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

/**
 * Created by marcus on 22-08-2016.
 */
public class ActivateCommand implements CommandExecutor {

    Core plugin;
    public ActivateCommand(Core instance) {
        plugin = instance;
    }
    public ArrayList<String> activated = new ArrayList<>();
    TitleManagerAPI titleManagerAPI = new TitleManagerAPI();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Nej!");
            return true;
        }
        if(command.getName().equalsIgnoreCase("pingtracker")) {
            Player p = (Player) sender;
            if(activated.contains(p.getName())) {
                activated.remove(p.getName());
                p.sendMessage("§8[§3DarkStarPvP§8] §cDu vil ikke længere se din ping tracker.");
            } else {
                activated.add(p.getName());
                p.sendMessage("§8[§3DarkStarPvP§8] §aDu vil nu se din ping tracker. Slå den fra med /pingtracker.");
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if(activated.contains(p.getName())) {
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
        return false;
    }
}
