package org.skylar11d.minecraftp.tntrun.utilities.assets.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.skylar11d.minecraftp.tntrun.Main;
import org.skylar11d.minecraftp.tntrun.utilities.manager.runner.Runner;

/**
 *  @Author Skylar11D
 */

public class PlayerRunEvent extends Event implements Cancellable {

    private boolean cancelled = false;
    Runner r;

    public PlayerRunEvent(Runner runner){

        this.r = runner;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
        Bukkit.getServer().getScheduler().cancelTask(Main.getInstance().getRunnerManager().getRunners().get(r).getTaskId());
    }

    public Runner getRunner() {
        return r;
    }



    private static final HandlerList handlerList = new HandlerList();
    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
    public static HandlerList getHandlerList(){
        return handlerList;
    }

}
