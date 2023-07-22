package org.skylar11d.minecraftp.tntrun.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.skylar11d.minecraftp.tntrun.Main;
import org.skylar11d.minecraftp.tntrun.utilities.manager.runner.Runner;

public class SpawnListener implements Listener {

    @EventHandler
    public void onEntitiesSpawning(CreatureSpawnEvent e){

            if(e.getEntityType() == EntityType.ARMOR_STAND ||
                    e.getEntityType() == EntityType.PAINTING ||
                    e.getEntityType() == EntityType.ITEM_FRAME ||
                    e.getEntityType() == EntityType.DROPPED_ITEM ||
                    e.getEntityType() == EntityType.PLAYER ||
                    e.getEntityType() == EntityType.UNKNOWN ||
                    e.getEntityType() == EntityType.ARROW ||
                    e.getEntityType() == EntityType.FIREBALL ||
                    e.getEntityType() == EntityType.SMALL_FIREBALL ||
                    e.getEntityType() == EntityType.FIREWORK ||
                    e.getEntityType() == EntityType.VILLAGER ||
                    e.getEntityType() == EntityType.THROWN_EXP_BOTTLE ||
                    e.getEntityType() == EntityType.COMPLEX_PART ||
                    e.getEntityType() == EntityType.FALLING_BLOCK ||
                    e.getEntityType() == EntityType.EXPERIENCE_ORB ||
                    e.getEntityType() == EntityType.SPLASH_POTION ||
                    e.getEntityType() == EntityType.MINECART ||
                    e.getEntityType() == EntityType.MINECART_CHEST ||
                    e.getEntityType() == EntityType.MINECART_TNT ||
                    e.getEntityType() == EntityType.MINECART_COMMAND ||
                    e.getEntityType() == EntityType.MINECART_FURNACE ||
                    e.getEntityType() == EntityType.MINECART_HOPPER ||
                    e.getEntityType() == EntityType.MINECART_MOB_SPAWNER ||
                    e.getEntityType() == EntityType.PRIMED_TNT ||
                    e.getEntityType() == EntityType.ENDER_CRYSTAL
            ){
                e.setCancelled(false);
            }
            else{
                e.setCancelled(true);
            }

    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e){
        Bukkit.getServer().getScheduler().runTaskLater(Main.getInstance(),
                () -> Main.getInstance().getRunnerManager().getRunner(e.getPlayer()).spectate(), 6L);
    }

}
