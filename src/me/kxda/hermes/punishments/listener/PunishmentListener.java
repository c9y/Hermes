package me.kxda.hermes.punishments.listener;

import lombok.Getter;
import me.kxda.hermes.Hermes;
import me.kxda.hermes.utils.CC;
import me.kxda.hermes.punishments.PunishmentManager;
import me.kxda.hermes.punishments.PunishmentType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public class PunishmentListener implements Listener {

    @Getter
    private PunishmentManager manager;
    @Getter
    private Hermes plugin;

    public PunishmentListener(final PunishmentManager manager) {
        this.plugin = Hermes.getPlugin ();
        this.manager = manager;
        this.plugin.getServer ().getPluginManager ().registerEvents ((Listener)this, (Plugin)this.plugin);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerLogin(final AsyncPlayerChatEvent event) {
        final UUID uuid = event.getPlayer ().getUniqueId ();
        final PunishmentManager.Punishment punishment = this.manager.hasActivePunishment (uuid, PunishmentType.MUTE);
        if (punishment != null) {
            event.setCancelled (true);
            event.getPlayer ().sendMessage ( CC.RED + "Your account is current muted " + punishment.getMuteTimeLeft () + ".");
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerBanLogin(final PlayerLoginEvent event) {
        final UUID uuid = event.getPlayer ().getUniqueId ();
        final PunishmentManager.Punishment punishment = this.manager.hasActivePunishment (uuid, PunishmentType.BANNED);
        if (punishment != null) {
            event.disallow(PlayerLoginEvent.Result.KICK_FULL, CC.RED + "You are permanently banned from Valor.\n" + CC.GRAY + "If you feel this ban is unjustified, visit http://valorpvp.club/appeal.\n" + CC.GOLD + "You may also purchase an unban at store.valorpvp.club." );
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerBlacklistLogin(final PlayerLoginEvent event) {
        final UUID uuid = event.getPlayer ().getUniqueId ();
        final PunishmentManager.Punishment punishment = this.manager.hasActivePunishment (uuid, PunishmentType.BLACKLISTED);
        if (punishment != null) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, CC.RED + "You are blacklisted from Valor.\n" + CC.GRAY + "You may not appeal this type of punishment.\n" + CC.DARK_RED + "You also may not purchase an unban for this type of punishment.");
        }
    }
}
