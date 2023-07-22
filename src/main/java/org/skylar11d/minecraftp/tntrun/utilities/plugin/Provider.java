package org.skylar11d.minecraftp.tntrun.utilities.plugin;

import org.skylar11d.minecraftp.tntrun.utilities.data.impl.IMySQL;

public interface Provider {

    void onEnable();
    default void onDisable(){}
    IMySQL getMySQL();

}
