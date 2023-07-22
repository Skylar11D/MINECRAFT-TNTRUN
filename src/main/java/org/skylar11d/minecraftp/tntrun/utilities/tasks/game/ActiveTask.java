package org.skylar11d.minecraftp.tntrun.utilities.tasks.game;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;
import org.skylar11d.minecraftp.tntrun.Main;
import org.skylar11d.minecraftp.tntrun.utilities.manager.game.GameManager;
import org.skylar11d.minecraftp.tntrun.utilities.manager.game.GameState;
import org.skylar11d.minecraftp.tntrun.utilities.manager.runner.Runner;
import org.skylar11d.minecraftp.tntrun.utilities.manager.runner.RunnerManager;

import java.util.Set;

public class ActiveTask extends BukkitRunnable {

    GameManager gameManager;
    RunnerManager runnerManager;

    public ActiveTask(GameManager gm){
        this.gameManager = gm;
        this.runnerManager = Main.getInstance().getRunnerManager();
    }

    @Override
    public void run() {

        Set<Runner> runners = runnerManager.getRunners().keySet();

        if(runners.size() < 2){

            Bukkit.getServer().getOnlinePlayers().forEach(
                    o -> o.playSound(o.getLocation(), Sound.LEVEL_UP, 5f, 5f)
            );

            Bukkit.broadcastMessage("§7§l§m=========================");
            Bukkit.broadcastMessage("");
            Bukkit.broadcastMessage("§e"+runners.stream().findFirst().get().getPlayer().getName() + " §a§lWON THE GAME");
            Bukkit.broadcastMessage("");
            Bukkit.broadcastMessage("§7§l§m=========================");

            gameManager.setGameState(GameState.ENDING);
            cancel();

        }

    }
}
