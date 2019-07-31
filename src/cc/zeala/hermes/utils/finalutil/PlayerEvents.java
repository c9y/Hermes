package cc.zeala.hermes.utils.finalutil;

import cc.zeala.hermes.commands.staff.MuteChatCommand;
import cc.zeala.hermes.profile.Profile;
import cc.zeala.hermes.ranks.Rank;
import cc.zeala.hermes.staff.AdminChatHandler;
import cc.zeala.hermes.staff.StaffChatHandler;
import cc.zeala.hermes.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class PlayerEvents implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Player player = event.getPlayer();
        Profile profile = new Profile(player.getUniqueId());
        if (profile.getRank().isAboveOrEqual(Rank.TMOD)) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                Profile profile2 = new Profile(p.getUniqueId());
                if (profile2.getRank().isAboveOrEqual(Rank.TMOD)) {
                    p.sendMessage(CC.AQUA + "[S] " + profile.getRank().getColor() + player.getName() + CC.D_AQUA + " connected to the NA-Practice.");
                }
            }
        }
    }

    @EventHandler
    public void onLeave(final PlayerQuitEvent event) {
        event.setQuitMessage(null);
        Player player = event.getPlayer();
        Profile profile = new Profile(player.getUniqueId());
        if (profile.getRank().isAboveOrEqual(Rank.TMOD)) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                Profile profile2 = new Profile(p.getUniqueId());
                if (profile2.getRank().isAboveOrEqual(Rank.TMOD)) {
                    p.sendMessage(CC.AQUA + "[S] " + profile.getRank().getColor() + player.getName() + CC.D_AQUA + " disconnected from the NA-Practice.");
                }
            }
        }
    }

    @EventHandler
    public void onPlayerChat(final AsyncPlayerChatEvent event) {
        final Player p = event.getPlayer ();
        final Profile profile = new Profile ( p.getUniqueId () );
        if (MuteChatCommand.muteToggled) {
            if (!profile.getRank ().isAboveOrEqual ( Rank.TMOD )) {
                event.setCancelled ( true );
                p.sendMessage ( ChatColor.translateAlternateColorCodes ( '&' , "&cChat is currently muted." ) );
            } else if (!profile.getRank ().isAboveOrEqual ( Rank.TMOD )) {
                event.setCancelled ( false );
            } else if (MuteChatCommand.muteToggled) {
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onStaffChat(final AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        if (StaffChatHandler.isStaffChat(player)) {
            event.setCancelled(true);
            for (final Player online : Bukkit.getServer().getOnlinePlayers()) {
                if (new Profile (online.getUniqueId()).getRank().isAboveOrEqual(Rank.TMOD)) {
                    online.sendMessage(CC.AQUA + "[S] " + new Profile (player.getUniqueId()).getRank().getColor() + player.getName() + CC.WHITE + ": " + CC.WHITE + event.getMessage());
                }
            }
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onAdminChat(final AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        if (AdminChatHandler.isAdminChat(player)) {
            event.setCancelled(true);
            for (final Player online : Bukkit.getServer().getOnlinePlayers()) {
                if (new Profile (online.getUniqueId()).getRank().isAboveOrEqual(Rank.TMOD)) {
                    online.sendMessage(CC.RED + "[A] " + new Profile (player.getUniqueId()).getRank().getColor() + player.getName() + CC.WHITE + ": " + CC.WHITE + event.getMessage());
                }
            }
        }
    }
    @EventHandler(priority = EventPriority.LOWEST)
    public void onCommandProcess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        if (event.getMessage().toLowerCase().startsWith("//calc") || event.getMessage().toLowerCase().startsWith("//eval") || event.getMessage().toLowerCase().startsWith("//solve") || event.getMessage().toLowerCase().startsWith("/bukkit:") || event.getMessage().toLowerCase().startsWith("/me") || event.getMessage().toLowerCase().startsWith("/bukkit:me") || event.getMessage().toLowerCase().startsWith("/minecraft:") || event.getMessage().toLowerCase().startsWith("/minecraft:me")) {
            player.sendMessage(ChatColor.RED + "You cannot perform this command.");
            event.setCancelled(true);
        }
    }
}
