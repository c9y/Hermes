package cc.zeala.hermes.commands.player;

import cc.zeala.hermes.profile.Profile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimeCommand {
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;
        Profile profile = new Profile(player.getUniqueId());
        if (command.getName().equalsIgnoreCase("night")) {

        }
        return false;
    }
}