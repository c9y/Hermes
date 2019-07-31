package cc.zeala.hermes.punishments.command;

import lombok.Getter;
import cc.zeala.hermes.Hermes;
import cc.zeala.hermes.profile.Profile;
import cc.zeala.hermes.ranks.Rank;
import cc.zeala.hermes.utils.CC;
import cc.zeala.hermes.utils.Messager;
import cc.zeala.hermes.utils.finalutil.PlayerUtil;
import cc.zeala.hermes.punishments.PunishmentManager;
import cc.zeala.hermes.punishments.PunishmentType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.UUID;

public class KickCommand implements CommandExecutor {

    @Getter
    private PunishmentManager manager;
    @Getter
    private Hermes plugin;

    public KickCommand(final PunishmentManager manager) {
        this.plugin = Hermes.getPlugin();
        this.manager = manager;
        this.plugin.getCommand("kick").setExecutor((CommandExecutor)this);
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length < 2) {
                Messager.sendMessage(sender, CC.RED + "Usage: /kick <target> [reason] [-s] ");
                return true;
            }
            else {
                final Player target = Bukkit.getPlayer (args[0]);
                if (!PlayerUtil.isOnline (sender, target)) {
                    Messager.sendMessage (sender, args[0] + CC.RED + "Player not found");
                    return true;
                }
                String reason = "";
                for (int i = 1; i < args.length; ++i) {
                    reason = reason + args[i] + " ";
                }
                if (reason.equalsIgnoreCase("-s")) {
                    Messager.sendMessage (sender, CC.RED + "Please provide a valid reason.");
                    return true;
                }
                reason = reason.replace("-s", "");
                final Profile profile = new Profile (target.getUniqueId());
                final Player player = (Player) sender;
                final Profile profile1 = new Profile(player.getUniqueId());
                final boolean silent = Arrays.asList(args).contains("-s");
                if (target.isOnline ()) {
                    target.getPlayer ().kickPlayer ( CC.RED + "You have been kicked for " + reason );
                }
                if (!silent) {
                    Bukkit.broadcastMessage (profile.getRank().getColor() + target.getName () + CC.GREEN +  " was kicked by " + profile1.getRank().getColor() + sender.getName() + CC.GREEN + ".");
                }
                else {
                    Messager.sendMessage ( sender, CC.RED + "STAFF ONLY: " + target.getName () + " was kicked by " + sender.getName () + ".");
                }
                final PunishmentManager manager = this.manager;
                final PunishmentType kick = PunishmentType.KICK;
                final UUID uniqueId = target.getUniqueId();
                final PunishmentManager manager2 = this.manager;
                manager.addPunishment(kick, uniqueId, null, PunishmentManager.PUNISHMENT_EXPIRED, reason);
            }
            return true;
        }
        final Player player = (Player) sender;
        final Profile profile = new Profile ( player.getUniqueId ());
        if (!profile.getRank ().isAboveOrEqual ( Rank.MOD )) {
            Messager.sendMessage(sender, CC.RED + "You don't have permission to use this command." );
            return true;
        }
        if (args.length < 2) {
            Messager.sendMessage(sender, CC.RED + "Usage: /kick <target> [reason] [-s] ");
            return true;
        }
        else {
            final Player target = Bukkit.getPlayer (args[0]);
            if (!PlayerUtil.isOnline (sender, target)) {
                Messager.sendMessage (sender, args[0] + CC.RED + "Player not found");
                return true;
            }
            String reason = "";
            for (int i = 1; i < args.length; ++i) {
                reason = reason + args[i] + " ";
            }
            if (reason.equalsIgnoreCase("-s")) {
                Messager.sendMessage (sender, CC.RED + "Please provide a valid reason.");
                return true;
            }
            reason = reason.replace("-s", "");
            final boolean silent = Arrays.asList(args).contains("-s");
            if (target.isOnline ()) {
                target.getPlayer ().kickPlayer ( CC.RED + "You have been kicked from Valor for " + reason + ".");
            }
            if (!silent) {
                final Profile profile1 = new Profile(player.getUniqueId());
                Bukkit.broadcastMessage (profile.getRank().getColor() + target.getName () + CC.GREEN +  " was kicked by " + profile1.getRank().getColor() + sender.getName() + CC.GREEN + ".");
            }
            else {
                final Profile profile1 = new Profile(player.getUniqueId());
                Messager.sendMessage ( sender, CC.RED + "STAFF ONLY: " + profile.getRank().getColor() + target.getName () + " was kicked by " + profile1.getRank().getColor() + sender.getName () + CC.GREEN + ".");
            }
            final PunishmentManager manager = this.manager;
            final PunishmentType kick = PunishmentType.KICK;
            final UUID uniqueId = target.getUniqueId();
            final UUID punisher = player.getUniqueId ();
            final PunishmentManager manager2 = this.manager;
            manager.addPunishment(kick, uniqueId, punisher, PunishmentManager.PUNISHMENT_EXPIRED, reason);
        }
        return true;
    }
}
