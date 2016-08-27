package me.itzmarcus.pingtracker;

import me.itzmarcus.pingtracker.commands.ActivateCommand;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by marcus on 22-08-2016.
 */
public class Core extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        getCommand("pingtracker").setExecutor(new ActivateCommand(this));
    }


}
