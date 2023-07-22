package org.skylar11d.minecraftp.tntrun;

import org.bukkit.configuration.file.FileConfiguration;
import org.skylar11d.minecraftp.tntrun.utilities.config.ConfigType;
import org.skylar11d.minecraftp.tntrun.utilities.data.MySQL;
import org.skylar11d.minecraftp.tntrun.utilities.data.impl.IMySQL;
import org.skylar11d.minecraftp.tntrun.utilities.plugin.PluginManager;
import org.skylar11d.minecraftp.tntrun.utilities.plugin.Provider;

import java.sql.Connection;

public class MainProvider implements Provider {

    private IMySQL iMySQL;

    @Override
    public void onEnable() {

        PluginManager.getPluginManager().initializeCommands();
        PluginManager.getPluginManager().initializeListeners();

        this.iMySQL = new MySQL(
                Main.getInstance().getConfigManager().getConfig(ConfigType.DATABASE).getString("database"),
                Main.getInstance().getConfigManager().getConfig(ConfigType.DATABASE).getString("host"),
                Main.getInstance().getConfigManager().getConfig(ConfigType.DATABASE).getInt("port"),
                Main.getInstance().getConfigManager().getConfig(ConfigType.DATABASE).getString("username"),
                Main.getInstance().getConfigManager().getConfig(ConfigType.DATABASE).getString("password")
        );

    }

    public IMySQL getMySQL() {
        return iMySQL;
    }

    /*@Override
    public void onDisable() {
        Provider.super.onDisable();
    }*/

}
