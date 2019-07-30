package me.kxda.hermes.commands.admin;

import lombok.Getter;
import me.kxda.hermes.Hermes;
import me.kxda.hermes.profile.Profile;
import me.kxda.hermes.ranks.Rank;
import me.kxda.hermes.task.ShutdownTask;
import me.kxda.hermes.utils.CC;
import me.kxda.hermes.utils.Messager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ShutdownCommand  implements CommandExecutor {

    @Getter
    private final Hermes plugin;
    @Getter
    private ShutdownTask shutdownTask;

    public ShutdownCommand(final Hermes plugin) {
        this.plugin = Hermes.getPlugin ();
        this.plugin.getCommand ("shutdown").setExecutor ((CommandExecutor)this);
    }

    @Override
    public boolean onCommand(CommandSender sender , Command command ,final  String s , final String[] args) {
        Player player = (Player)sender;
        Profile profile = new Profile(player.getUniqueId());
        if (!profile.getRank().isAboveOrEqual(Rank.ADMIN)) {
            Messager.sendMessage(sender, CC.RED + "You don't have permission to use this command." );
            return true;
        }
        if (args.length != 1) {
            sender.sendMessage(CC.RED + "Please use /shutdown <seconds | time | cancel>");
            return true;
        }
        if (args[0].equalsIgnoreCase("time")) {
            if (this.shutdownTask == null) {
                sender.sendMessage(CC.RED + "The command is not scheduled to shut down.");
            }
            else {
                sender.sendMessage(CC.GREEN + "The command will shutdown in " + this.shutdownTask.getSecondsUntilShutdown() + " seconds.");
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("cancel")) {
            if (this.shutdownTask == null) {
                sender.sendMessage(CC.RED + "The command is not scheduled to shut down.");
            }
            else {
                this.shutdownTask.cancel();
                this.shutdownTask = null;
                sender.sendMessage(CC.RED + "The command shutdown has been canceled.");
            }
        }
        else {
            int seconds;
            try {
                seconds = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException e) {
                sender.sendMessage(CC.RED + "You must input a valid number!");
                return true;
            }
            if (seconds <= 0) {
                sender.sendMessage(CC.RED + "You must input a number greater than 0!");
                return true;
            }
            if (this.shutdownTask == null) {
                (this.shutdownTask = new ShutdownTask(this.plugin, seconds)).runTaskTimer((Plugin ) this.plugin, 20L, 20L);
            }
            else {
                this.shutdownTask.setSecondsUntilShutdown(seconds);
            }
            sender.sendMessage(CC.GREEN + "The command will shutdown in " + seconds + " seconds.");
        }
        return true;
    }
}
