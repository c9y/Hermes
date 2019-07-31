package cc.zeala.hermes.commands.admin;

import cc.zeala.hermes.utils.Messager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import cc.zeala.hermes.profile.Profile;
import cc.zeala.hermes.ranks.Rank;
import cc.zeala.hermes.utils.CC;

public class EatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        Profile profile = new Profile(player.getUniqueId());

        if (!profile.getRank().isAboveOrEqual(Rank.ADMIN)) {
            Messager.sendMessage(sender, CC.RED + "You don't have permission to use this command." );
            return true;
        }

        player.sendMessage(CC.translate("&aYou have been fed."));
        player.setFoodLevel(20);
        return false;
    }

}