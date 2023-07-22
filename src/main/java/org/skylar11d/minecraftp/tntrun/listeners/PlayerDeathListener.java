package org.skylar11d.minecraftp.tntrun.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.skylar11d.minecraftp.tntrun.Main;
import org.skylar11d.minecraftp.tntrun.utilities.manager.runner.Runner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlayerDeathListener implements Listener {

    ExecutorService pool = Executors.newCachedThreadPool();

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getEntity().getPlayer();


       // pool.execute(() -> {
        Bukkit.getServer().getScheduler().runTaskLater(Main.getInstance(), () -> p.spigot().respawn(), 4);
        //});

    }

}
