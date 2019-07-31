package cc.zeala.hermes.commands.staff;

import lombok.Getter;
import cc.zeala.hermes.Hermes;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import cc.zeala.hermes.profile.Profile;
import cc.zeala.hermes.ranks.Rank;
import cc.zeala.hermes.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand implements CommandExecutor {

    @Getter
    private final Hermes plugin;

    public ClearChatCommand(final Hermes plugin) {
        this.plugin = Hermes.getPlugin ();
    }

    @Override
    public boolean onCommand(final CommandSender sender ,final Command command , final String s , final String[] strings) {
        final Player player = (Player)sender;
        final Profile profile = new Profile(player.getUniqueId());
        if (profile.getRank().isAboveOrEqual(Rank.MOD)) {
            for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                final Profile profile1 = new Profile(online.getUniqueId());
                if (!profile1.getRank().isStaff()) {
                    online.sendMessage(new String[101]);
                    online.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aChat was cleared by " + profile.getRank().getColor() + sender.getName() + "&a."));
                }
            }
            sender.sendMessage(CC.translate("&aChat was cleared by " + profile.getRank().getColor() + sender.getName() + "&a."));
        }
        else {
            sender.sendMessage(CC.translate("&cNo Permissions."));
        }
        return false;
    }
}
