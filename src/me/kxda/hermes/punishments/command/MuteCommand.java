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

import java.util.UUID;

public class MuteCommand implements CommandExecutor {

    @Getter
    private PunishmentManager manager;
    @Getter
    private Hermes plugin;

    public MuteCommand(final PunishmentManager manager) {
        this.plugin = Hermes.getPlugin ();
        this.manager = manager;
        this.plugin.getCommand ( "mute" ).setExecutor ( ( CommandExecutor ) this );
    }

    public boolean onCommand(final CommandSender sender , final Command command , final String label , final String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length < 2) {
                Messager.sendMessage (sender, CC.RED + "Usage: /mute <target> <time> <reason> [-s] ");
            }
            else {
                final OfflinePlayer target = Bukkit.getOfflinePlayer ( args[0] );
                if (!target.hasPlayedBefore () && !target.isOnline ()) {
                    Messager.sendMessage ( sender , CC.RED + args[0] + " has never played before." );
                    return true;
                }
                if (this.manager.hasActivePunishment (target.getUniqueId (), PunishmentType.MUTE) != null) {
                    Messager.sendMessage (sender, CC.RED + target.getName () + " is already muted.");
                    return true;
                }
                String reason = "";
                for (int i = 1; i < args.length; ++i) {
                    reason = reason + args[i] + " ";
                }
                if (reason.equalsIgnoreCase ("-s")) {
                    Messager.sendMessage (sender, CC.RED + target.getName () + " was muted permanently by " + sender.getName () + ".");
                }
                else {
                    command.broadcastCommandMessage (sender, CC.RED + "STAFF ONLY: " + target.getName () + " was muted permanently by " + sender.getName () + " for " + reason + ".");
                }
                if (target.isOnline ()) {
                    target.getPlayer ().sendMessage ("");
                    target.getPlayer ().sendMessage (CC.RED + "You have been muted by " + sender.getName () + " for " + reason + ".");
                    target.getPlayer ().sendMessage ("");
                }
                final PunishmentManager manager = this.manager;
                final PunishmentType mute = PunishmentType.MUTE;
                final UUID uniqueId = target.getUniqueId();
                final PunishmentManager manager2 = this.manager;
                manager.addPunishment(mute, uniqueId, null, PunishmentManager.PUNISHMENT_EXPIRE_PERMANENTLY, reason);
            }
            return true;
        }
        final Player player = (Player)sender;
        final Profile profile = new Profile (player.getUniqueId ());
        if (!profile.getRank ().isAboveOrEqual ( Rank.TMOD)) {
            Messager.sendMessage(sender, CC.RED + "You don't have permission to use this command." );
            return true;
        }
        if (args.length < 2) {
            Messager.sendMessage (sender, CC.RED + "Usage: /mute <target> <reason> [-s] ");
        }
        else {
            final OfflinePlayer target = Bukkit.getOfflinePlayer ( args[0] );
            if (!target.hasPlayedBefore () && !target.isOnline ()) {
                Messager.sendMessage ( sender , CC.RED + args[0] + " has never played before." );
                return true;
            }
            if (this.manager.hasActivePunishment (target.getUniqueId (), PunishmentType.MUTE) != null) {
                Messager.sendMessage (sender, CC.RED + target.getName () + " is already muted.");
                return true;
            }
            String reason = "";
            for (int i = 1; i < args.length; ++i) {
                reason = reason + args[i] + " ";
            }
            if (reason.equalsIgnoreCase ("-s")) {
                Messager.sendMessage (sender, CC.RED + target.getName () + " was temporarily muted by " + sender.getName () + ".");
            }
            else {
                Messager.sendMessage ( sender, CC.RED + "STAFF ONLY: " + target.getName () + " was muted permanently by " + sender.getName () + " for " + reason + ".");
            }
            if (target.isOnline ()) {
                target.getPlayer ().sendMessage ("");
                target.getPlayer ().sendMessage (CC.RED + "You have been muted by " + sender.getName () + " for " + reason + ".");
                target.getPlayer ().sendMessage ("");
            }
            final PunishmentManager manager = this.manager;
            final PunishmentType mute = PunishmentType.MUTE;
            final UUID uniqueId = target.getUniqueId();
            final PunishmentManager manager2 = this.manager;
            manager.addPunishment(mute, uniqueId, null, PunishmentManager.PUNISHMENT_EXPIRE_PERMANENTLY, reason);
        }
        return true;
    }
}
