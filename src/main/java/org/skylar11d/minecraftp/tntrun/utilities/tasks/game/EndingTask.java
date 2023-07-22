package org.skylar11d.minecraftp.tntrun.utilities.tasks.game;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.skylar11d.minecraftp.tntrun.Main;
import org.skylar11d.minecraftp.tntrun.utilities.manager.game.GameManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class EndingTask extends BukkitRunnable {

    GameManager gameManager;
    int counter = 7;

    public EndingTask(GameManager gm){
        this.gameManager = gm;
    }

    @Override
    public void run() {

        counter--;
        if(counter <= 0)Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"restart");

    }
}
