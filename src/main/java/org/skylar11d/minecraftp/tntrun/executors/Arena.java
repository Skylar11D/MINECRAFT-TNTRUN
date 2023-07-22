package org.skylar11d.minecraftp.tntrun.executors;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.skylar11d.minecraftp.tntrun.Main;
import org.skylar11d.minecraftp.tntrun.utilities.command.ACommand;
import org.skylar11d.minecraftp.tntrun.utilities.command.ICommand;

/**
 *  @Author Skylar11D
 */

@ACommand(name = "arena", permission = "tntrun.admin", requiresPlayer = true)
public class Arena extends ICommand {

    @Override
    public void execute(Player p, String @NotNull [] args) {

        if(args.length != 2) {
            p.sendMessage("§c/arena set <waiting/starting>");
            return;
        }

        if(!args[0].equalsIgnoreCase( "set")) {
            p.sendMessage("§c/arena 'set' <waiting/starting>");
            return;
        }

        if(args[1].equalsIgnoreCase("waiting")){
            Main.getInstance().getLocations().save("waiting", p.getLocation());
            p.sendMessage("§aThe waiting arena has successfully been set");
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 10f, 10f);
        }
        if(args[1].equalsIgnoreCase("starting")){
            Main.getInstance().getLocations().save("starting", p.getLocation());
            p.sendMessage("§aThe starting arena has successfully been set");
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 10f, 10f);
        }

    }

}
