package me.kxda.hermes.commands.staff;

import me.kxda.hermes.Hermes;
import me.kxda.hermes.profile.Profile;
import me.kxda.hermes.ranks.Rank;
import me.kxda.hermes.utils.CC;
import me.kxda.hermes.utils.Messager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class FreezeCommand implements CommandExecutor {

    private Hermes plugin;

    public FreezeCommand(final Hermes plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] args) {
        Player player = (Player)sender;
        Profile profile = new Profile(player.getUniqueId());
        if (!profile.getRank().isAboveOrEqual(Rank.TMOD)) {
            Messager.sendMessage(sender, CC.RED + "You don't have permission to use this command." );
            return true;
        }
        if (args.length != 1) {
            return false;
        }
        final Player target = this.plugin.getServer().getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Player not found.");
            return true;
        }
        if (this.plugin.getManagerHandler().getFrozenManager().isFrozen(target.getUniqueId())) {
            sender.sendMessage(ChatColor.GREEN + "You have been unfrozen " + target.getName());
            this.plugin.getServer().broadcast(ChatColor.RED + target.getName() + ChatColor.YELLOW + " has been unfrozen by " + ChatColor.RED + ((sender instanceof Player) ? sender.getName() : "Console"), "core.staff");
            this.unfreezePlayer(target);
            return true;
        }
        sender.sendMessage(ChatColor.GREEN + "You have been frozen " + target.getName());
        this.plugin.getServer().broadcast(ChatColor.RED + target.getName() + ChatColor.GOLD + " has been frozen by " + ChatColor.RED + ((sender instanceof Player) ? sender.getName() : "Console"), "core.staff");
        this.freezePlayer(target);
        return true;
    }

    private void freezePlayer(final Player player) {
        this.plugin.getManagerHandler().getPlayerSnapshotManager().takeSnapshot(player);
        this.plugin.getManagerHandler().getFrozenManager().freezeUUID(player.getUniqueId());
        player.sendMessage(ChatColor.GREEN + "You have been frozen! Don't logout!");
        player.getInventory().clear();
        player.getInventory().setArmorContents((ItemStack[])null);
        player.setWalkSpeed(0.0f);
        this.clearPotionEffects(player);
        player.updateInventory();
        player.openInventory(this.plugin.getManagerHandler().getInventoryManager().getFrozenInv());
    }

    private void unfreezePlayer(final Player player) {
        this.plugin.getManagerHandler().getFrozenManager().unfreezeUUID(player.getUniqueId());
        player.sendMessage(ChatColor.GREEN + "You have been unfrozen by staff.");
        player.closeInventory();
        this.plugin.getManagerHandler().getPlayerSnapshotManager().restorePlayer(player);
    }

    private void clearPotionEffects(final Player player) {
        for (final PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
    }
}

