package me.kxda.hermes.commands.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.kxda.hermes.profile.Profile;
import me.kxda.hermes.ranks.Rank;
import me.kxda.hermes.utils.CC;

public class RanksCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            Player player = (Player) sender;
            Profile profile = new Profile(player.getUniqueId());
            if (profile.getRank().isAboveOrEqual(Rank.MOD)) {
                player.sendMessage(CC.SEPARATOR);
                player.sendMessage("");
                player.sendMessage(CC.GOLD + "Ranks:");
                player.sendMessage(CC.SEPARATOR);
                player.sendMessage(Rank.OWNER.getInfo());
                player.sendMessage(Rank.DEVELOPER.getInfo());
                player.sendMessage(Rank.MANAGER.getInfo());
                player.sendMessage(Rank.PLATADMIN.getInfo());
                player.sendMessage(Rank.ADMIN.getInfo());
                player.sendMessage(Rank.SENIORMOD.getInfo());
                player.sendMessage(Rank.MOD.getInfo());
                player.sendMessage(Rank.TRAINEE.getInfo());
                player.sendMessage(Rank.PARTNER.getInfo());
                player.sendMessage(Rank.FAMOUS.getInfo());
                player.sendMessage(Rank.YOUTUBE.getInfo());
                player.sendMessage(Rank.BUILDER.getInfo());
                player.sendMessage(Rank.HERO.getInfo());
                player.sendMessage(Rank.MVP.getInfo());
                player.sendMessage(Rank.VIP.getInfo());
                player.sendMessage(Rank.NORMAL.getInfo());
                player.sendMessage("");
                player.sendMessage(CC.SEPARATOR);
            }
            return true;
    }
}
