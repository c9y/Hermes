package me.kxda.hermes.punishments.command;

import lombok.Getter;
import me.kxda.hermes.Hermes;
import me.kxda.hermes.profile.Profile;
import me.kxda.hermes.punishments.PunishmentManager;
import me.kxda.hermes.ranks.Rank;
import me.kxda.hermes.utils.CC;
import me.kxda.hermes.utils.Duration;
import me.kxda.hermes.utils.Messager;
import me.kxda.hermes.punishments.PunishmentType;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class TempBanCommand implements CommandExecutor {

    @Getter
    private PunishmentManager manager;
    @Getter
    private Hermes plugin;

    public TempBanCommand(final PunishmentManager manager) {
        this.plugin = Hermes.getPlugin ();
        this.manager = manager;
        this.plugin.getCommand ( "tempban" ).setExecutor ( ( CommandExecutor ) this );
    }

    public boolean onCommand(final CommandSender sender , final Command command , final String label , final String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length < 3) {
                Messager.sendMessage (sender, CC.RED + "Usage: /tempban <target> <time> [reason] [-s] ");
            }
            else {
                final OfflinePlayer target = Bukkit.getOfflinePlayer ( args[0] );
                if (!target.hasPlayedBefore () && !target.isOnline ()) {
                    Messager.sendMessage(sender, CC.RED + target.getName () + " has never played before, but will be banned." );
                }
                final Long time = Duration.parse(args[1]);
                if (time == null) {
                    Messager.sendMessage (sender, CC.RED + args[1] + " is not valid a duration.");
                }
                if (time < 0L) {
                    Messager.sendMessage (sender, CC.RED + "Invalid duration: 3m3s");
                    return true;
                }
                if (this.manager.hasActivePunishment (target.getUniqueId (), PunishmentType.BANNED) != null) {
                    Messager.sendMessage (sender, CC.RED + target.getName () + " is already banned");
                    return true;
                }
                String reason = "";
                for (int i = 2; i < args.length; ++i) {
                    reason = reason + args[i] + " ";
                }
                if (reason.equalsIgnoreCase("-s")) {
                    Messager.sendMessage (sender, CC.RED + "Please provide a valid reason.");
                    return true;
                }
                reason = reason.replace("-s", "");
                final boolean silent = Arrays.asList(args).contains("-s");
                if (target.isOnline()) {
                    target.getPlayer().kickPlayer(CC.RED + "You are banned from Valor " + CC.GRAY + "If you feel this ban is unjustified, visit http://valorpvp.club/appeal.\n" + CC.GOLD + "You may also purchase an unban at store.valorpvp.club.");
                }
                if (!silent) {
                    Bukkit.broadcastMessage ( CC.GREEN + target.getName () + " was temporally banned by " + sender.getName() + ".");
                } else {
                    Messager.sendMessage ( sender , CC.RED + "ONLY STAFF: " + target.getName () + " was banned permanently by " + sender.getName () + " for " + reason + '.' );
                }
                this.manager.addPunishment(PunishmentType.BANNED, target.getUniqueId(), null, System.currentTimeMillis() + time, reason);
            }
            return true;
        }
        final Player player = (Player)sender;
        final Profile profile = new Profile(player.getUniqueId ());
        if (!profile.getRank ().isAboveOrEqual (Rank.TMOD)) {
            Messager.sendMessage(sender, CC.RED + "You don't have permission to use this command." );
            return true;
        }
        if (args.length < 3) {
            Messager.sendMessage (sender, CC.RED + "Usage: /tempban <target> <time> [reason] [-s] ");
        }
        else {
            final OfflinePlayer target = Bukkit.getOfflinePlayer ( args[0] );
            if (!target.hasPlayedBefore () && !target.isOnline ()) {
                Messager.sendMessage(sender, CC.RED + target.getName () + " has never played before, but will be banned." );
            }
            final Long time = Duration.parse(args[1]);
            if (time == null) {
                Messager.sendMessage (sender, CC.RED + args[1] + " is not valid a duration.");
            }
            if (time < 0L) {
                Messager.sendMessage (sender, CC.RED + "Invalid duration");
                return true;
            }
            if (this.manager.hasActivePunishment (target.getUniqueId (), PunishmentType.BANNED) != null) {
                Messager.sendMessage (sender, CC.RED + target.getName () + " is already banned");
                return true;
            }
            String reason = "";
            for (int i = 2; i < args.length; ++i) {
                reason = reason + args[i] + " ";
            }
            if (reason.equalsIgnoreCase("-s")) {
                Messager.sendMessage (sender, CC.RED + "Please provide a valid reason.");
                return true;
            }
            reason = reason.replace("-s", "");
            final boolean silent = Arrays.asList(args).contains("-s");
            if (target.isOnline()) {
                target.getPlayer().kickPlayer(CC.RED + "You are banned from Valor for %s.\n" + CC.GRAY + "If you feel this ban is unjustified, visit https://Valorpvp.club/appeal.\n" + CC.GOLD + "You may also purchase an unban at store.valorpvp.club");
            }
            if (!silent) {
                Bukkit.broadcastMessage ( CC.GREEN + target.getName () + " was temporally banned by " + sender.getName () + " for " + reason + '.' );
            } else {
                Messager.sendMessage ( sender , CC.RED + "ONLY STAFF: " + target.getName () + " was banned permanently by " + sender.getName () + " for " + reason + '.' );
            }
            this.manager.addPunishment(PunishmentType.TEMPBAN, target.getUniqueId(), null, System.currentTimeMillis() + time, reason);
        }
        return true;
    }
}
