package org.skylar11d.minecraftp.tntrun.utilities.manager.runner;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.skylar11d.minecraftp.tntrun.utilities.tasks.ingame.blockEliminationTask;

import java.util.HashMap;
import java.util.Map;

public class RunnerManager {

    /**
     *  @Author Skylar11D
     */


    //private final Set<Runner> runners;
    private final Map<Runner, BukkitRunnable> runners;

    public RunnerManager(){
        //this.runners = new HashSet<>();
        this.runners = new HashMap<>();
    }

    public Runner getRunner(Player p) {

        return runners.keySet().stream().filter(r -> r.getPlayer() == p).findFirst().orElse(null);
    }

    public Map<Runner, BukkitRunnable> getRunners(){return runners;}

    public void registerRunner(Player p){
        runners.put(() -> p, new blockEliminationTask() {
            @Override
            public Player getPlayer() {
                return p;
            }
        });

        getRunner(p).prepareBoard();
        if(!getRunner(p).checkDatabaseExistence())
            getRunner(p).prepareDataBaseInfo();

    }

    public void unregisterRunner(Player p){
        getRunners().remove(getRunner(p));
    }

    public boolean isRegistered(Player p){
        return getRunners().containsKey(
                getRunners().keySet().stream().filter(r -> r.getPlayer() == p).findFirst().orElse(null)
        );
    }

}
