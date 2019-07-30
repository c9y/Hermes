package me.kxda.hermes.commands.player;

import me.kxda.hermes.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.concurrent.ThreadLocalRandom;

public class DiscordCommand implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] arguments) {
        if (command.getName().equalsIgnoreCase("discord")) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
            sender.sendMessage(CC.WHITE + "Join our discord: " + CC.BLUE + "https://discord.gg/gECbTA6");
            return true;
        }
        return false;
    }
}
