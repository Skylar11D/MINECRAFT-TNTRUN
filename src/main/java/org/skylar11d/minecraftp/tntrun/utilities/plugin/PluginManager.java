package org.skylar11d.minecraftp.tntrun.utilities.plugin;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.skylar11d.minecraftp.tntrun.Main;
import org.skylar11d.minecraftp.tntrun.executors.Arena;
import org.skylar11d.minecraftp.tntrun.listeners.*;
import org.skylar11d.minecraftp.tntrun.utilities.assets.events.PlayerRunEvent;
import org.skylar11d.minecraftp.tntrun.utilities.command.ICommand;

import java.util.logging.Level;

public class PluginManager {

    private Listener[] listeners = {
            new ConnectionListener(), new MovementsListener(), new SpawnListener(),
            new ClimateChangeListener(), new PlayerRunningListener(), new PlayerDeathListener()
    };
    private ICommand[] iCommands = {new Arena()};
    static PluginManager pm;
    static {pm = new PluginManager();}

    public void initializeListeners(){
        for(Listener listener : listeners){
            Bukkit.getServer().getPluginManager().registerEvents(listener, Main.getPlugin(Main.class));
            Bukkit.getLogger().log(Level.INFO, "[PluginManager] the listener "+listener.getClass().getSimpleName()+" has been registered");
        }
    }

    public void initializeCommands(){
        for(ICommand iCommand : iCommands){
            Main.getPlugin(Main.class).getCommand(iCommand.getaCommand().name()).setExecutor(iCommand);
            Bukkit.getLogger().log(Level.INFO, "[PluginManager] the command /"+iCommand.getaCommand().name()+" has been set");
        }
    }

    public static PluginManager getPluginManager() {return pm;}
}
