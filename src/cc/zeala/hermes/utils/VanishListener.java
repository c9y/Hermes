package cc.zeala.hermes.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class VanishListener {
    private final ArrayList<UUID> inVanish;

    public VanishListener() {
        this.inVanish = new ArrayList<UUID>();
    }

    public boolean isVanished(final Player player) {
        return this.inVanish.contains(player.getUniqueId());
    }

    public void Vanish(final Player p, final boolean bool) {
        if (bool) {
            this.inVanish.add(p.getUniqueId());
            for (final Player player : Bukkit.getServer().getOnlinePlayers()) {
                if (!player.hasPermission("hermes.staff")) {
                    player.hidePlayer(p);
                }
            }
        } else {
            this.inVanish.remove(p.getUniqueId());
            for (final Player player : Bukkit.getServer().getOnlinePlayers()) {
                player.showPlayer(p);
            }
        }
    }
}