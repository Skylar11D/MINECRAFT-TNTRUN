package org.skylar11d.minecraftp.tntrun.utilities.manager.game;

import org.bukkit.plugin.Plugin;
import org.skylar11d.minecraftp.tntrun.utilities.tasks.game.ActiveTask;
import org.skylar11d.minecraftp.tntrun.utilities.tasks.game.EndingTask;
import org.skylar11d.minecraftp.tntrun.utilities.tasks.game.StartingTask;
import org.skylar11d.minecraftp.tntrun.utilities.tasks.game.WaitingTask;

public class GameManager {

    private Plugin plugin;
    private GameState gameState;
    public GameManager(Plugin plugin, GameState gameState){
        this.plugin = plugin;
        setGameState(gameState);
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        if(gameState == this.gameState)return;
        this.gameState = gameState;

        switch (gameState){
            case WAITING: {
                new WaitingTask(this).runTaskTimer(plugin, 1, 20*1);
                break;
            }
            case STARTING: {
                new StartingTask(this).runTaskTimer(plugin, 1, 20);
                break;
            }
            case ACTIVE: {
                new ActiveTask(this).runTaskTimer(plugin, 1, 20);
                break;
            }
            case ENDING: {
                new EndingTask(this).runTaskTimer(plugin, 1, 20);
                break;
            }
        }

    }
}
