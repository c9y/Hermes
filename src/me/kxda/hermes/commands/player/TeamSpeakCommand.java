package me.kxda.hermes.commands.player;

import me.kxda.hermes.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.concurrent.ThreadLocalRandom;

public class TeamSpeakCommand implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] arguments) {
        if (command.getName().equalsIgnoreCase("teamspeak")) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
            sender.sendMessage(CC.WHITE + "Join our teamspeak: " + CC.BLUE + "ts.valorpvp.club");
            return true;
        }
        return false;
    }
}
