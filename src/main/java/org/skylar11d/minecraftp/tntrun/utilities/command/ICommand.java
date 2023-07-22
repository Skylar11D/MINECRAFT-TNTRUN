package org.skylar11d.minecraftp.tntrun.utilities.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

/**
 *  @Author Skylar11D
 */

public abstract class ICommand implements CommandExecutor {

    private ACommand aCommand;

    public ICommand(){
        this.aCommand = getClass().getDeclaredAnnotation(ACommand.class);
        Objects.requireNonNull(aCommand, "Command mustn't be left without annotation");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!getaCommand().permission().isEmpty()){
            if(!sender.hasPermission(getaCommand().permission())){
                sender.sendMessage(ChatColor.RED + "You can't access this command (permission required)");
                return false;
            }
        }

        if(getaCommand().requiresPlayer()){
            if(!(sender instanceof Player)) {
                sender.sendMessage("You're not qualified to execute this command");
                return true;
            }
            execute((Player) sender, args);
        }

        execute(sender, args);

        return true;
    }

    public void execute(Player player, String[] cmdArgs){}
    public void execute(CommandSender sender, String[] cmdArgs){}

    public ACommand getaCommand() {
        return aCommand;
    }
}
