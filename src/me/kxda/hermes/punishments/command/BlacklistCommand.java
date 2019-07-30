package me.kxda.hermes.punishments.command;

import me.kxda.hermes.Hermes;
import me.kxda.hermes.profile.Profile;
import me.kxda.hermes.ranks.Rank;
import me.kxda.hermes.utils.CC;
import me.kxda.hermes.utils.Messager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.*;
import me.kxda.hermes.punishments.PunishmentManager;
import me.kxda.hermes.punishments.PunishmentType;

public class BlacklistCommand implements CommandExecutor
{
    private PunishmentManager manager;
    private Hermes plugin;

    public BlacklistCommand(final PunishmentManager manager) {
        this.plugin = Hermes.getPlugin();
        this.manager = manager;
        this.plugin.getCommand("blacklist").setExecutor((CommandExecutor)this);
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length < 1) {
                Messager.sendMessage(sender, CC.RED + "Usage: /blacklist <target> [reason] [-s] ");
            }
            else {
                final OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                if (!target.hasPlayedBefore() && !target.isOnline()) {
                    Messager.sendMessage(sender, CC.RED + args[0] + " has never played before.");
                    return true;
                }
                String reason = "";
                for (int i = 2; i < args.length; ++i) {
                    reason = reason + args[i] + " ";
                }
                if (reason.equalsIgnoreCase("-s")) {
                    Messager.sendMessage(sender, CC.RED + "Please provide a valid reason.");
                    return true;
                }
                reason = reason.replace("-s", "");
                final boolean silent = Arrays.asList(args.length).contains("-s");
                if (target.isOnline()) {
                    target.getPlayer().kickPlayer(CC.DARK_RED + "You are blacklisted from Valor.\n" + CC.GRAY + "You may not appeal this type of punishment.\n" + CC.DARK_RED + "You also may not purchase an unban for this type of punishment.");
                }
                if (!silent) {
                    Messager.sendMessage ( sender, CC.GREEN + target.getName() + " was blacklisted by " + sender.getName() + ".");
                }
                else {
                    Messager.sendMessage ( sender, CC.RED + "ONLY STAFF: " + target.getName() + " was blacklisted by " + sender.getName() + ".");
                }
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "ban " + target.getName() + ' ' + reason + " -s");
            }
            return true;
        }
        final Player player = (Player)sender;
        final Profile profile = new Profile(player.getUniqueId());
        if (!profile.getRank().isAboveOrEqual(Rank.ADMIN)) {
            Messager.sendMessage(sender, CC.RED + "You don't have permission to use this command.");
            return true;
        }
        if (args.length < 1) {
            Messager.sendMessage(sender, CC.RED + "Usage: /blacklist <target> [reason] [-s] ");
        }
        else {
            final OfflinePlayer target2 = Bukkit.getOfflinePlayer(args[0]);
            if (!target2.hasPlayedBefore() && !target2.isOnline()) {
                Messager.sendMessage(sender, CC.RED + args[0] + " has never played before.");
                return true;
            }
            String reason2 = "";
            for (int j = 2; j < args.length; ++j) {
                reason2 = reason2 + args[j] + " ";
            }
            if (reason2.equalsIgnoreCase("-s")) {
                Messager.sendMessage(sender, CC.RED + "Please provide a valid reason.");
                return true;
            }
            reason2 = reason2.replace("-s", "");
            final boolean silent2 = Arrays.asList(args.length).contains("-s");
            if (target2.isOnline()) {
                target2.getPlayer().kickPlayer(CC.DARK_RED + "You are blacklisted from Valor.\n" + CC.GRAY + "You may not appeal this type of punishment.\n" + CC.DARK_RED + "You may also not purchase an unban for this type of punishment.");
            }
            if (!silent2) {
                Bukkit.broadcastMessage(CC.GREEN + target2.getName() + " was blacklisted by " + sender.getName() + ".");
            }
            else {
                Messager.sendMessage ( sender, CC.RED + "ONLY STAFF: " + target2.getName() + " was blacklisted by " + sender.getName() + ".");
            }
            Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "ban " + target2.getName() + ' ' + reason2 + " -s");
            final PunishmentManager manager = this.manager;
            final PunishmentType blacklisted = PunishmentType.BLACKLISTED;
            final UUID uniqueId = target2.getUniqueId ();
            manager.addPunishment(blacklisted, uniqueId, null, PunishmentManager.PUNISHMENT_EXPIRE_PERMANENTLY, reason2);
        }
        return true;
    }

    public PunishmentManager getManager() {
        return this.manager;
    }

    public Hermes getPlugin() {
        return this.plugin;
    }
}

