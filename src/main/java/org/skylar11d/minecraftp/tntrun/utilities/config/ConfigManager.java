package org.skylar11d.minecraftp.tntrun.utilities.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.skylar11d.minecraftp.tntrun.Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;

public class ConfigManager {

    private File database;
    private File locations;
    private File settings;
    private FileConfiguration databaseC;
    private FileConfiguration locationsC;
    private FileConfiguration settingsC;

    public ConfigManager(){

        database = new File(Main.getInstance().getDataFolder() + "/database.yml");
        locations = new File(Main.getInstance().getDataFolder() + "/locations.yml");
        settings = new File(Main.getInstance().getDataFolder() + "/settings.yml");

        try {

            if(!database.exists())
                Files.copy(Main.getInstance().getResource("database.yml"), database.toPath());
            if(!locations.exists())
                Files.copy(Main.getInstance().getResource("locations.yml"), locations.toPath());
            if(!settings.exists())
                Files.copy(Main.getInstance().getResource("settings.yml"), settings.toPath());

        } catch (IOException e){
            e.printStackTrace();
            Bukkit.getLogger().log(Level.WARNING, "[ConfigManager] Failed to load the plugin's files");
        }
    }

    /*public void initialize(){
        database = new File(Main.getInstance().getDataFolder() + "/database.yml");
        locations = new File(Main.getInstance().getDataFolder() + "/locations.yml");
        settings = new File(Main.getInstance().getDataFolder() + "/settings.yml");


        try {

            if(!database.exists())
                Files.copy(Main.getInstance().getResource("database.yml"), database.toPath());
            if(!locations.exists())
                Files.copy(Main.getInstance().getResource("locations.yml"), locations.toPath());
            if(!settings.exists())
                Files.copy(Main.getInstance().getResource("settings.yml"), settings.toPath());

        } catch (IOException e){e.printStackTrace();}

    }*/

    public FileConfiguration getConfig(ConfigType type){

        databaseC = YamlConfiguration.loadConfiguration(database);
        locationsC = YamlConfiguration.loadConfiguration(locations);
        settingsC = YamlConfiguration.loadConfiguration(settings);


        if(type == ConfigType.LOCATIONS)
            return locationsC;

        if(type == ConfigType.SETTINGS)
            return settingsC;

        if (type == ConfigType.DATABASE)
            return databaseC;

        return null;
    }

}
