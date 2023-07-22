package org.skylar11d.minecraftp.tntrun.utilities.manager.locations;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.skylar11d.minecraftp.tntrun.Main;
import org.skylar11d.minecraftp.tntrun.utilities.config.ConfigType;

import java.io.File;
import java.io.IOException;

public class ArenaLocation {

    public Location of(String arenaName){
        FileConfiguration lFC = Main.getInstance().getConfigManager().getConfig(ConfigType.LOCATIONS);

        if(!lFC.contains("arenas."+arenaName))return null;

        double x = lFC.getDouble("arenas."+arenaName+".x");
        double y = lFC.getDouble("arenas."+arenaName+".y");
        double z = lFC.getDouble("arenas."+arenaName+".z");
        float yaw = (float) lFC.getDouble("arenas."+arenaName+".yaw");
        float pitch = (float) lFC.getDouble("arenas."+arenaName+".pitch");
        String worldName = lFC.getString("arenas."+arenaName+".world");

        return new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
    }

    public void save(String byName, Location location){
        FileConfiguration lFC = Main.getInstance().getConfigManager().getConfig(ConfigType.LOCATIONS);

        lFC.set("arenas." + byName + ".x", location.getX());
        lFC.set("arenas." + byName + ".y", location.getY());
        lFC.set("arenas." + byName + ".z", location.getZ());
        lFC.set("arenas." + byName + ".yaw", location.getYaw());
        lFC.set("arenas." + byName + ".pitch", location.getPitch());
        lFC.set("arenas." + byName + ".world", location.getWorld().getName());

        try {
            lFC.save(new File(Main.getInstance().getDataFolder() + "/locations.yml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
