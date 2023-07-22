package org.skylar11d.minecraftp.tntrun;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.skylar11d.minecraftp.tntrun.utilities.config.ConfigManager;
import org.skylar11d.minecraftp.tntrun.utilities.manager.locations.ArenaLocation;
import org.skylar11d.minecraftp.tntrun.utilities.manager.runner.RunnerManager;
import org.skylar11d.minecraftp.tntrun.utilities.manager.game.GameManager;
import org.skylar11d.minecraftp.tntrun.utilities.manager.game.GameState;
import org.skylar11d.minecraftp.tntrun.utilities.plugin.Provider;

/**
 *  @Author Skylar11D
 */

public class Main extends JavaPlugin {

    private Provider provider;
    private static Main instance;
    private GameManager gameManager;
    private RunnerManager runnerManager;
    private ArenaLocation locationManager;
    private ConfigManager configManager;
    /*private File database;
    private File locations;
    private File settings;
    private FileConfiguration databaseC;
    private FileConfiguration locationsC;
    private FileConfiguration settingsC;*/
    @Override
    public void onEnable() {
        long timer = System.currentTimeMillis();
        System.out.println("initializing plugin's declared variables..");
        instance = this;
        provider = new MainProvider();
        gameManager = new GameManager(this, GameState.WAITING);
        runnerManager = new RunnerManager();
        configManager = new ConfigManager();
        locationManager = new ArenaLocation();

        provider.onEnable();

        System.out.println("setting plugin's properties up..");
        Bukkit.getOnlinePlayers().forEach(runnerManager::registerRunner);
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "weather clear");
        Bukkit.getServer().getWorld("world").setAutoSave(false);

        System.out.println("setting up plugin's files..");
        if(!getDataFolder().exists()) getDataFolder().mkdir();


           /* database = new File(getDataFolder() + "/database.yml");
        locations = new File(getDataFolder() + "/locations.yml");
        settings = new File(getDataFolder() + "/settings.yml");

            try {

                if(!database.exists())
                    Files.copy(getResource("database.yml"), database.toPath());
                if(!locations.exists())
                    Files.copy(getResource("locations.yml"), locations.toPath());
                if(!settings.exists())
                    Files.copy(getResource("settings.yml"), settings.toPath());

            } catch (IOException e){e.printStackTrace();}*/

        long time = (System.currentTimeMillis() - timer);
        System.out.println("This plugin has been loaded within "+time+"ms");
    }

    public static Main getInstance() {
        return instance;
    }

    public Provider getProvider() {
        return provider;
    }

    public static String C(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    @SuppressWarnings(value = "unused")
    public GameManager getGameManager() {
        return gameManager;
    }

    public RunnerManager getRunnerManager() {
        return runnerManager;
    }

    public ArenaLocation getLocations() {
        return locationManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}
