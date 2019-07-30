package me.kxda.hermes.commands.player;

import me.kxda.hermes.profile.Profile;
import me.kxda.hermes.ranks.Rank;
import me.kxda.hermes.utils.CC;
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