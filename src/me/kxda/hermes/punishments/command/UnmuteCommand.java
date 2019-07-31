 package me.kxda.hermes.punishments.command;

import lombok.Getter;
import me.kxda.hermes.Hermes;
import me.kxda.hermes.profile.Profile;
import me.kxda.hermes.ranks.Rank;
import me.kxda.hermes.utils.CC;
import me.kxda.hermes.utils.Messager;
import me.kxda.hermes.punishments.PunishmentManager;
import me.kxda.hermes.punishments.PunishmentType;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

 public class UnmuteCommand implements CommandExecutor {

    @Getter
    private Hermes plugin;
    @Getter
    private PunishmentManager manager;

    public UnmuteCommand(final PunishmentManager manager) {
        this.plugin = Hermes.getPlugin ();
        this.manager = manager;
        this.plugin.getCommand ( "unmute" ).setExecutor ( ( CommandExecutor ) this );
    }

    public boolean onCommand(final CommandSender sender , final Command command , final String label , final String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length < 1) {
                Messager.sendMessage (sender, CC.RED + "Usage: /unmute <target> [reason] [-s] ", CC.YELLOW + " Remove a player's mute.\nAdd a \"-s\" at the end to silently unmute a player.", "");
            }
            else {
                final OfflinePlayer target = Bukkit.getOfflinePlayer ( args[0] );
                final PunishmentManager.Punishment punishment = this.manager.hasActivePunishment ( target.getUniqueId () , PunishmentType.MUTE );
                if (punishment == null) {
                    Messager.sendMessage ( sender , CC.RED + target.getName () + " is not muted." );
                    return true;
                }
                punishment.removePunishment();
                final boolean silent = Arrays.asList(args).contains("-s");
                if (!silent) {
                    Bukkit.broadcastMessage(CC.GREEN + target.getName() + " was unmuted by " + sender.getName() + '.');
                }
                else {
                    Messager.sendMessage ( sender, CC.RED + "STAFF ONLY: " + target.getName () + " was unmuted by " + sender.getName () + ".");
                }
            }
            return true;
        }
        final Player player = (Player)sender;
        final Profile profile = new Profile(player.getUniqueId ());
        if (!profile.getRank ().isAboveOrEqual (Rank.TMOD)) {
            Messager.sendMessage(sender, CC.RED + "You don't have permission to use this command." );
            return true;
        }
        if (args.length < 1) {
            Messager.sendMessage (sender, CC.RED + "Usage: /unmute <target> [reason] [-s] ", CC.YELLOW + " Remove a player's mute.\nAdd a \"-s\" at the end to silently unmute a player.", "");
        }
        else {
            final OfflinePlayer target = Bukkit.getOfflinePlayer ( args[0] );
            final PunishmentManager.Punishment punishment = this.manager.hasActivePunishment ( target.getUniqueId () , PunishmentType.MUTE );
            if (punishment == null) {
                Messager.sendMessage ( sender , CC.RED + target.getName () + " is not muted." );
                return true;
            }
            punishment.removePunishment();
            final boolean silent = Arrays.asList(args).contains("-s");
            if (!silent) {
                Bukkit.broadcastMessage(CC.GREEN + target.getName() + " was unmuted by " + sender.getName() + '.');
            }
            else {
                Messager.sendMessage ( sender, CC.RED + "STAFF ONLY: " + target.getName () + " was unmuted by " + sender.getName () + ".");
            }
        }
        return true;
    }
}
