package cc.zeala.hermes.commands.staff;


import cc.zeala.hermes.profile.Profile;
import cc.zeala.hermes.ranks.Rank;
import cc.zeala.hermes.staff.StaffChatHandler;
import cc.zeala.hermes.utils.ColorText;
import cc.zeala.hermes.utils.finalutil.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChatCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ColorText.translate("&cYou must be player to execute this commands."));
            return true;
        }
        final Player player = (Player)sender;
        Profile profile = new Profile(player.getUniqueId());
        if (!profile.getRank().isAboveOrEqual(Rank.TMOD)) {
            player.sendMessage(ColorText.translate(StringUtil.NO_PERMISSION));
            return true;
        }
        StaffChatHandler.setStaffChat(player, !StaffChatHandler.isStaffChat(player));
        return true;
    }
}
