package cc.zeala.hermes.commands.player;

import cc.zeala.hermes.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.concurrent.ThreadLocalRandom;

public class HermesCommand implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] arguments) {
        if (command.getName().equalsIgnoreCase("hermes")) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
            sender.sendMessage(CC.GOLD + "Hermes " + CC.WHITE + "was coded by" + CC.D_PURPLE + " kxda & Palistine");
            return true;
        }
        return false;
    }
}