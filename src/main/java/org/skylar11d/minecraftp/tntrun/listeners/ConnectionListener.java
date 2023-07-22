package org.skylar11d.minecraftp.tntrun.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.skylar11d.minecraftp.tntrun.Main;
import org.skylar11d.minecraftp.tntrun.utilities.manager.game.GameState;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionListener implements Listener {

    private ExecutorService pool = Executors.newCachedThreadPool();

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        int size = Bukkit.getOnlinePlayers().size();
        int maximum = Bukkit.getServer().getMaxPlayers();
        Location waitingArena = Main.getInstance().getLocations().of("waiting");
        e.setJoinMessage(null);

        pool.execute(() -> {

            try {
                p.teleport(waitingArena);
            } catch (Exception e1){e1.printStackTrace();p.sendMessage("§ccannot find the waiting arena!");}

            if(Main.getInstance().getGameManager().getGameState().equals(GameState.ACTIVE)){
                Bukkit.getServer().getOnlinePlayers().forEach(o -> o.hidePlayer(p));
                p.addPotionEffects(
                        Arrays.asList(new PotionEffect(PotionEffectType.NIGHT_VISION, 90*9999, 90*999),
                                new PotionEffect(PotionEffectType.SLOW_DIGGING, 90*9999, 90*999),
                                new PotionEffect(PotionEffectType.INVISIBILITY, 90*9999, 90*999)
                                )
                );
            } else {
                Main.getInstance().getRunnerManager().registerRunner(p);
                Bukkit.broadcastMessage(Main.C("&c"+p.getName()+" &fwants to run! &7(&c"+size+"&7/&c"+maximum+"&7)"));
            }

        });

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Main.getInstance().getRunnerManager().unregisterRunner(e.getPlayer());
    }

}
