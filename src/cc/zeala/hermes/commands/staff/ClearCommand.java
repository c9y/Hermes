package cc.zeala.hermes.commands.staff;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import cc.zeala.hermes.utils.command.annotation.CommandInfo;
import cc.zeala.hermes.utils.command.annotation.Default;

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
