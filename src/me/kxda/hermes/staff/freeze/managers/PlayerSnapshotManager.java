package me.kxda.hermes.staff.freeze.managers;

import me.kxda.hermes.utils.PlayerSnapshot;
import me.kxda.hermes.Hermes;
import me.kxda.hermes.staff.freeze.Manager;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerSnapshotManager extends Manager
{
    private Map<UUID, PlayerSnapshot> playerSnapshotMap;
    
    public PlayerSnapshotManager(final Hermes plugin) {
        super(plugin);
        this.playerSnapshotMap = new HashMap<UUID, PlayerSnapshot>();
    }
    
    public void takeSnapshot(final Player player) {
        this.playerSnapshotMap.put(player.getUniqueId(), new PlayerSnapshot(player));
    }
    
    public void restorePlayer(final Player player) {
        final PlayerSnapshot playerSnapshot = this.getSnapshot(player);
        if (playerSnapshot != null) {
            player.getInventory().clear();
            player.getInventory().setContents(playerSnapshot.getMainContent());
            player.getInventory().setArmorContents(playerSnapshot.getArmorContent());
            player.setWalkSpeed(playerSnapshot.getWalkSpeed());
            player.addPotionEffects((Collection)playerSnapshot.getPotionEffects());
            player.updateInventory();
            this.removeSnapshot(player);
        }
    }
    
    private void removeSnapshot(final Player player) {
        this.playerSnapshotMap.remove(player.getUniqueId());
    }
    
    private PlayerSnapshot getSnapshot(final Player player) {
        return this.playerSnapshotMap.get(player.getUniqueId());
    }
}
