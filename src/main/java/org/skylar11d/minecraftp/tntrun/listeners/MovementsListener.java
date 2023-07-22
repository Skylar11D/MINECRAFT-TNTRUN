package org.skylar11d.minecraftp.tntrun.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.skylar11d.minecraftp.tntrun.Main;
import org.skylar11d.minecraftp.tntrun.utilities.assets.events.PlayerRunEvent;
import org.skylar11d.minecraftp.tntrun.utilities.config.ConfigType;
import org.skylar11d.minecraftp.tntrun.utilities.manager.game.GameManager;
import org.skylar11d.minecraftp.tntrun.utilities.manager.game.GameState;
import org.skylar11d.minecraftp.tntrun.utilities.manager.runner.Runner;
import org.skylar11d.minecraftp.tntrun.utilities.manager.runner.RunnerManager;

public class MovementsListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        RunnerManager rm = Main.getInstance().getRunnerManager();
        Runner runner = rm.getRunner(p);
        GameManager gm = Main.getInstance().getGameManager();
        Location sandLoc = p.getLocation().subtract(0, 1, 0);
        Location tntLoc = p.getLocation().subtract(0, 2, 0);
        FileConfiguration settings = Main.getInstance().getConfigManager().getConfig(ConfigType.SETTINGS);

        if (!rm.isRegistered(p))
            return;

        if(gm.getGameState() != GameState.ACTIVE)
            return;

        if (sandLoc.getBlock().getType() == Material.SAND) {
            if (tntLoc.getBlock().getType() == Material.TNT) {
                Bukkit.getPluginManager().callEvent(new PlayerRunEvent(runner));
            }
        }

        if(p.getLocation().getY() < -settings.getInt("void-height")){
            p.teleport(p.getLocation().add(0,15, 0));
            Bukkit.getWorld(p.getWorld().getName()).strikeLightning(p.getLocation().add(0,3,0));
            Bukkit.broadcastMessage(ChatColor.RED + p.getName() + ChatColor.YELLOW + " has been eliminated!");
            runner.getPlayer().setHealth(0);
        }

    }

}
