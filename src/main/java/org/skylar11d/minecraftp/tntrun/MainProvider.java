package org.skylar11d.minecraftp.tntrun;

import org.bukkit.configuration.file.FileConfiguration;
import org.skylar11d.minecraftp.tntrun.utilities.config.ConfigType;
import org.skylar11d.minecraftp.tntrun.utilities.data.MySQL;
import org.skylar11d.minecraftp.tntrun.utilities.data.impl.IMySQL;
import org.skylar11d.minecraftp.tntrun.utilities.plugin.PluginManager;
import org.skylar11d.minecraftp.tntrun.utilities.plugin.Provider;

public class MainProvider implements Provider {

    private IMySQL iMySQL;
    private final FileConfiguration databaseConfiguration;

    public MainProvider(Main clazz){
        this.databaseConfiguration = clazz.getConfigManager().getConfig(ConfigType.DATABASE);
    }

    @Override
    public void onEnable() {

        this.iMySQL = new MySQL(
                databaseConfiguration.getString("database"),
                databaseConfiguration.getString("host"),
                databaseConfiguration.getInt("port"),
                databaseConfiguration.getString("username"),
                databaseConfiguration.getString("password")
        );

        PluginManager.getPluginManager().initializeCommands();
        PluginManager.getPluginManager().initializeListeners();

    }

    public IMySQL getMySQL() {
        return iMySQL;
    }

    /*@Override
    public void onDisable() {
        Provider.super.onDisable();
    }*/

}
