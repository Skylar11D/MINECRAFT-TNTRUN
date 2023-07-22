package org.skylar11d.minecraftp.tntrun.utilities.tasks.ingame;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class blockEliminationTask extends BukkitRunnable {
    public abstract Player getPlayer();

    @Override
    public void run() {
        Player actualPlayer = getPlayer();
        Location sandLoc = actualPlayer.getLocation().subtract(0, 1, 0);
        Location tntLoc = actualPlayer.getLocation().subtract(0, 2, 0);

        sandLoc.getBlock().setType(Material.AIR);
        tntLoc.getBlock().setType(Material.AIR);
        Bukkit.getServer().getWorld(sandLoc.getWorld().getName()).playEffect(sandLoc, Effect.SMOKE, 5);

    }

}
