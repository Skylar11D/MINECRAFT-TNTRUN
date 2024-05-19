package org.skylar11d.minecraftp.tntrun.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.skylar11d.minecraftp.tntrun.Main;
import org.skylar11d.minecraftp.tntrun.utilities.assets.events.PlayerRunEvent;
import org.skylar11d.minecraftp.tntrun.utilities.manager.runner.Runner;
import org.skylar11d.minecraftp.tntrun.utilities.manager.runner.RunnerManager;

public class PlayerRunningListener implements Listener {

    @EventHandler
    public void onRunning(PlayerRunEvent e){
        RunnerManager runnerManager = Main.getInstance().getRunnerManager();
        Runner runner = e.getRunner();
        Player player = e.getRunner().getPlayer();

        runnerManager.getRunners().get(runner).runTaskLater(Main.getPlugin(Main.class), 14);

    }

}
