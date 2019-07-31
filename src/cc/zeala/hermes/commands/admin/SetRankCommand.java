package cc.zeala.hermes.commands.admin;

import cc.zeala.hermes.profile.Profile;
import cc.zeala.hermes.ranks.Rank;
import cc.zeala.hermes.utils.CC;
import cc.zeala.hermes.utils.Messager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetRankCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        if (!(sender instanceof Player)) {
            if(arguments.length != 2) {
                Messager.sendMessage(sender, ChatColor.RED + "Usage: /setrank <player> <rank>");
                return true;
            }
            OfflinePlayer target = Bukkit.getOfflinePlayer(arguments[0]);
            if (!target.hasPlayedBefore() && !target.isOnline()) {
                sender.sendMessage(Messager.translate("&c" + arguments[0] + " has never played before."));
                return true;
            }
            Profile profiletarget = new Profile(target.getUniqueId());
            if (!profiletarget.isExists()) {
                sender.sendMessage(Messager.translate("&cPlayer not found in database."));
                return true;
            }
            Rank playerrank = null;
            try {
                if (arguments[0].equalsIgnoreCase( "normal")) {
                    playerrank = Rank.NORMAL;
                }
                if (arguments[0].equalsIgnoreCase( "vip")) {
                    playerrank = Rank.VIP;
                }
                if (arguments[0].equalsIgnoreCase( "mvp")) {
                    playerrank = Rank.MVP;
                }
                if (arguments[0].equalsIgnoreCase( "hero")) {
                    playerrank = Rank.HERO;
                }
                if (arguments[0].equalsIgnoreCase( "builder")) {
                    playerrank = Rank.BUILDER;
                }
                if (arguments[0].equalsIgnoreCase( "youtuber")) {
                    playerrank = Rank.YOUTUBE;
                }
                if (arguments[0].equalsIgnoreCase( "famous")) {
                    playerrank = Rank.FAMOUS;
                }
                if (arguments[0].equalsIgnoreCase( "partner")) {
                    playerrank = Rank.PARTNER;
                }
                else if (arguments[0].equalsIgnoreCase("trialmod") || (arguments[0].equalsIgnoreCase("tmod") || (arguments[0].equalsIgnoreCase("trial-mod")))) {
                    playerrank = Rank.TMOD;
                }
                else if (arguments[0].equalsIgnoreCase("mod") || (arguments[0].equalsIgnoreCase("moderator"))) {
                    playerrank = Rank.MOD;
                }
                else if (arguments[0].equalsIgnoreCase("srmod") || (arguments[0].equalsIgnoreCase("srmoderator"))) {
                    playerrank = Rank.SRMOD;
                }
                else if (arguments[0].equalsIgnoreCase("admin")) {
                    playerrank = Rank.ADMIN;
                }
                if (arguments[0].equalsIgnoreCase( "platadmin")) {
                    playerrank = Rank.PLATADMIN;
                }
                if (arguments[0].equalsIgnoreCase( "manager")) {
                    playerrank = Rank.MANAGER;
                }
                else if (arguments[0].equalsIgnoreCase("owner")) {
                    playerrank = Rank.OWNER;
                }
                else if (arguments[0].equalsIgnoreCase("developer")) {
                    playerrank = Rank.DEVELOPER;
                } else {
                    playerrank = Rank.valueOf(arguments[1].toUpperCase());
                }
            } catch (Exception e) {
                Messager.sendMessage(sender, "&cCould not find that rank.");
                return true;
            }
            if (playerrank != null) {
                profiletarget.setRank(playerrank);
                if (target.isOnline()) {
                    String updated = "&aUpdated %name%%s% &arank to %rank%.";
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', (updated).replace("%name%", target.getName())).replace("%s%", target.getName().endsWith("s") ? "'" : "'s").replace("%rank%", profiletarget.getRank().getColor() + profiletarget.getRank().getName()));
                    target.getPlayer().sendMessage(Messager.translate("&aYou have been given the " + profiletarget.getRank().getColor() + profiletarget.getRank().getName() + " &arank"));
                    return false;
                }
            }
            return true;
        }
        Player player = (Player)sender;
        Profile profile = new Profile(player.getUniqueId());
        if (!profile.getRank().isAboveOrEqual(Rank.ADMIN)) {
            Messager.sendMessage(sender, CC.RED + "You don't have permission to use this command." );
            return true;
        }
        if(arguments.length != 2) {
            Messager.sendMessage(sender, ChatColor.RED + "Usage: /setrank <player> <rank>");
            return true;
        }
        OfflinePlayer target = Bukkit.getOfflinePlayer(arguments[0]);
        if (!target.hasPlayedBefore() && !target.isOnline()) {
            player.sendMessage(Messager.translate("&c" + arguments[0] + " has never played before."));
            return true;
        }
        Profile profiletarget = new Profile(target.getUniqueId());
        if (!profiletarget.isExists()) {
            player.sendMessage(Messager.translate("&cPlayer not found in database."));
            return true;
        }
        Rank playerrank = null;
        try {
            if (arguments[0].equalsIgnoreCase( "normal")) {
                playerrank = Rank.NORMAL;
            }
            else if (arguments[0].equalsIgnoreCase( "vip")) {
                playerrank = Rank.VIP;
            }
            else if (arguments[0].equalsIgnoreCase( "mvp")) {
                playerrank = Rank.MVP;
            }
            else if (arguments[0].equalsIgnoreCase( "hero")) {
                playerrank = Rank.HERO;
            }
            else if (arguments[0].equalsIgnoreCase( "builder")) {
                playerrank = Rank.BUILDER;
            }
            else if (arguments[0].equalsIgnoreCase( "youtuber")) {
                playerrank = Rank.YOUTUBE;
            }
            else if (arguments[0].equalsIgnoreCase( "famous")) {
                playerrank = Rank.FAMOUS;
            }
            else if (arguments[0].equalsIgnoreCase( "partner")) {
                playerrank = Rank.PARTNER;
            }
            else if (arguments[0].equalsIgnoreCase("trialmod") || (arguments[0].equalsIgnoreCase("tmod") || (arguments[0].equalsIgnoreCase("trial-mod")))) {
                playerrank = Rank.TMOD;
            }
            else if (arguments[0].equalsIgnoreCase("mod") || (arguments[0].equalsIgnoreCase("moderator"))) {
                playerrank = Rank.MOD;
            }
            else if (arguments[0].equalsIgnoreCase("srmod") || (arguments[0].equalsIgnoreCase("srmoderator"))) {
                playerrank = Rank.SRMOD;
            }
            else if (arguments[0].equalsIgnoreCase("admin")) {
                playerrank = Rank.ADMIN;
            }
            else if (arguments[0].equalsIgnoreCase( "platadmin")) {
                    playerrank = Rank.PLATADMIN;
            }
            else if (arguments[0].equalsIgnoreCase( "manager")) {
                playerrank = Rank.MANAGER;
            }
            else if (arguments[0].equalsIgnoreCase("owner")) {
                playerrank = Rank.OWNER;
            }
            else if (arguments[0].equalsIgnoreCase("developer")) {
                playerrank = Rank.DEVELOPER;
            } else {
                playerrank = Rank.valueOf(arguments[1].toUpperCase());
            }
        } catch (Exception e) {
            Messager.sendMessage(sender, "&cCould not find that rank.");
            return true;
        }
        if (playerrank != null) {
            profiletarget.setRank(playerrank);
            if (target.isOnline()) {
                String updated = "&aUpdated %name%%s% &arank to %rank%.";
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', (updated).replace("%name%", target.getName())).replace("%s%", target.getName().endsWith("s") ? "'" : "'s").replace("%rank%", profiletarget.getRank().getColor() + profiletarget.getRank().getName()));
                target.getPlayer().sendMessage(Messager.translate("&aYou have been given the " + profiletarget.getRank().getColor() + profiletarget.getRank().getName() + " &arank"));
                return false;
            }
        }
        return true;
    }
}