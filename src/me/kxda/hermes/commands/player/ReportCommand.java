package me.kxda.hermes.commands.player;

import lombok.Getter;
import me.kxda.hermes.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportCommand implements CommandExecutor {

    @Getter
    public static String arraylist;

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("report")) {
            if (sender instanceof Player) {
                final Player p = (Player)sender;
                final Profile profile = new Profile ( p.getUniqueId () );
                if (args.length >= 2) {
                    final Player target = Bukkit.getPlayer(args[0]);
                    if (sender == target) {
                        sender.sendMessage(ChatColor.RED + "You cannot report yourself!");
                        return true;
                    }
                    if (target != null) {
                        final String report = ChatColor.GREEN + "Your report has been submitted.";
                        final StringBuilder buffer = new StringBuilder();
                        for (int i = 1; i < args.length; ++i) {
                            buffer.append(' ').append(args[i]);
                        }
                        final String reason = buffer.toString();
                        p.sendMessage(String.valueOf("" + report));
                        for (final Player staffer : Bukkit.getServer().getOnlinePlayers()) {
                            if (staffer.hasPermission("hermes.staff")) {
                                staffer.sendMessage("§7[§5Report§7] " + new Profile (p.getUniqueId()).getRank().getColor() + p.getName() + ChatColor.GRAY + " has reported " + new Profile (target.getUniqueId()).getRank().getColor() + target.getName() + ChatColor.GRAY + " for" + ChatColor.LIGHT_PURPLE + reason);
                            }
                        }
                    }
                    else {
                        p.sendMessage("Target not found");
                    }
                }
                else {
                    p.sendMessage(ChatColor.RED + "/report <player> <reason>");
                }
            }
            else {
                sender.sendMessage("");
            }
        }
        return true;
    }
}

