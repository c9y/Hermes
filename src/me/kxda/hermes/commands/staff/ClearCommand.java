package me.kxda.hermes.commands.staff;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.kxda.hermes.utils.command.annotation.CommandInfo;
import me.kxda.hermes.utils.command.annotation.Default;

@CommandInfo(
        name = "clear",
        permission = "onu.clear",
        usage = "/clear"
)

public class ClearCommand {

    @Default
    public void clear(CommandSender sender) {
        Player p = (Player) sender;
        p.getInventory().clear();
    }

}
