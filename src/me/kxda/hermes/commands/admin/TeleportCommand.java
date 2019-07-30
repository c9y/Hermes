package me.kxda.hermes.commands.admin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.kxda.hermes.profile.Profile;
import me.kxda.hermes.ranks.Rank;
import me.kxda.hermes.utils.CC;

public class TeleportCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;
        Profile profile = new Profile(player.getUniqueId());
        if (command.getName().equalsIgnoreCase("teleport")) {
            if (!profile.getRank().isAboveOrEqual(Rank.ADMIN)) {
                CC.translate("&cNo Permission.");

                if (args.length != 1) {
                    player.sendMessage(CC.translate("&cPlease specify a player to teleport to."));
                    return false;
                }
                Player target = Bukkit.getServer().getPlayer(args[1]);
                if (target == null) {
                    player.sendMessage(CC.translate("&cPlayer not found."));
                    return false;
                }
                Location location = target.getLocation();
                player.teleport(location);
                player.sendMessage(CC.translate("&7You have been teleported to &4" + target.getName()));
                return false;
            }
        }
        return false;
    }
}

