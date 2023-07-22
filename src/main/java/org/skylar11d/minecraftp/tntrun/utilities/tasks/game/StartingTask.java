package org.skylar11d.minecraftp.tntrun.utilities.tasks.game;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;
import org.skylar11d.minecraftp.tntrun.Main;
import org.skylar11d.minecraftp.tntrun.utilities.assets.TitleType;
import org.skylar11d.minecraftp.tntrun.utilities.manager.game.GameManager;
import org.skylar11d.minecraftp.tntrun.utilities.manager.game.GameState;
import org.skylar11d.minecraftp.tntrun.utilities.manager.runner.Runner;
import org.skylar11d.minecraftp.tntrun.utilities.manager.runner.RunnerManager;

import java.util.Set;

public class StartingTask extends BukkitRunnable {

    GameManager gameManager;
    RunnerManager runnerManager;
    int counter = 10;
    int size = Bukkit.getOnlinePlayers().size();

    public StartingTask(GameManager gm){
        this.gameManager = gm;
        this.runnerManager = Main.getInstance().getRunnerManager();
    }

    @Override
    public void run() {

        Set<Runner> runners = runnerManager.getRunners().keySet();

        if (size >= 2) {
            counter--;

            switch (counter) {
                case 3: {
                    runners.forEach(r -> r.sendTitle("§a§l➌", "§4run!", TitleType.TITLE));
                    runners.forEach(r -> r.getPlayer().playSound(r.getPlayer().getLocation(), Sound.NOTE_PIANO, 5f, 5f));
                }
                case 2: {
                    runners.forEach(r -> r.sendTitle("§e§l➋", "§4run!", TitleType.TITLE));
                    runners.forEach(r -> r.getPlayer().playSound(r.getPlayer().getLocation(), Sound.NOTE_PIANO, 5f, 5f));
                }
                case 1: {
                    runners.forEach(r -> r.sendTitle("§c§l➊", "§4run!", TitleType.TITLE));
                    runners.forEach(r -> r.getPlayer().playSound(r.getPlayer().getLocation(), Sound.NOTE_PIANO, 5f, 5f));
                    break;
                }
                case 0: {
                    gameManager.setGameState(GameState.ACTIVE);
                    //^ activates PlayerRunEvent(called in PlayerMovementsListener, Line: 41,36) and the ActiveTask
                    cancel();
                }
            }

        } else {
            Bukkit.broadcastMessage("§cThe game has been cancelled: no enough players!");
            gameManager.setGameState(GameState.WAITING);
            //return back to the waiting lobby
            cancel();
        }
    }

}
