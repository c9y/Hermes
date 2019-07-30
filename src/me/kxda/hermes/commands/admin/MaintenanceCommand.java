package me.kxda.hermes.commands.admin;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.kxda.hermes.utils.TranslateUtil;
import me.kxda.hermes.utils.command.annotation.CommandInfo;
import me.kxda.hermes.utils.command.annotation.Default;

@CommandInfo(
        name = "maintenance",
        usage = "&c/maintenance <on|off>",
        permission = "onu.maintenance",
        noPermissionMessage = "No Permissions."
)
public class MaintenanceCommand {

    public String[] usage = TranslateUtil.translate(getClass().getAnnotation(CommandInfo.class).usage());

    @Default
    public void execute(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(usage);
        } else {
            sender.sendMessage(usage);
        }
    }

}
