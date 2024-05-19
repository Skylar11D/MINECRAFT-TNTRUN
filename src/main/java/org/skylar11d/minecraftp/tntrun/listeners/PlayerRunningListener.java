package org.skylar11d.minecraftp.tntrun.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.skylar11d.minecraftp.tntrun.Main;
import org.skylar11d.minecraftp.tntrun.utilities.assets.events.PlayerRunEvent;
import org.skylar11d.minecraftp.tntrun.utilities.manager.runner.Runner;
import org.skylar11d.minecraftp.tntrun.utilities.manager.runner.RunnerManager;

import java.util.concurrent.*;

public class PlayerRunningListener implements Listener {

    ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @EventHandler
    public void onRunning(@NotNull PlayerRunEvent e){
        RunnerManager runnerManager = Main.getInstance().getRunnerManager();
        Runner runner = e.getRunner();

        pool.execute(() -> runnerManager.getRunners().get(runner).runTaskLater(Main.getPlugin(Main.class), 6));
    }

}
