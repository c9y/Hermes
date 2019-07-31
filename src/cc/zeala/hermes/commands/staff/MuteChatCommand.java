package cc.zeala.hermes.commands.staff;

import lombok.Getter;
import cc.zeala.hermes.profile.Profile;
import cc.zeala.hermes.ranks.Rank;
import cc.zeala.hermes.utils.CC;
import cc.zeala.hermes.utils.Messager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteChatCommand implements CommandExecutor {

    @Getter
    public static boolean muteToggled;

    @Override
    public boolean onCommand(final CommandSender sender , final Command cmd , final String label , final String[] args) {
        final Player player = ( Player ) sender;
        final Profile profile = new Profile ( player.getUniqueId () );
        if (!profile.getRank ().isAboveOrEqual ( Rank.TMOD )) {
            Messager.sendMessage ( sender , CC.RED + "You don't have permission to use this command." );
            return true;
        }
        final Player p = (Player)sender;
        if (!cmd.getName ().equalsIgnoreCase ( "mutechat" ) || args.length != 0 || cmd.getName ().equalsIgnoreCase ( "mutechat" )) {
        }
        if (!MuteChatCommand.muteToggled && profile.getRank ().isAboveOrEqual ( Rank.TMOD)) {
            MuteChatCommand.muteToggled = true;
            Bukkit.broadcastMessage ( ChatColor.RED + "Chat was muted by " + player.getName () + "." );
        } else if (MuteChatCommand.muteToggled && profile.getRank ().isAboveOrEqual ( Rank.TMOD)) {
            MuteChatCommand.muteToggled = false;
            Bukkit.broadcastMessage ( ChatColor.GREEN + "Chat was unmuted by " + player.getName () + "." );
        }
        return false;
    }

    static {
        MuteChatCommand.muteToggled = false;
    }
}
