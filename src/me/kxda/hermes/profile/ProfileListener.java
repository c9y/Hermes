package me.kxda.hermes.profile;

import lombok.Getter;
import me.kxda.hermes.utils.CC;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Snowball;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import me.kxda.hermes.Hermes;
import me.kxda.hermes.ranks.Rank;
import org.bukkit.event.Listener;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class ProfileListener implements Listener {

    @Getter
    private Hermes plugin;

    @EventHandler
    public void onConnect(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Profile profile = new Profile(player.getUniqueId());
        if(!profile.isExists()) {
            profile.create();
        }
        Rank rank = profile.getRank();
        String userTag = rank.getPrefix() + rank.getColor() + player.getName();
        if (!player.getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', userTag))) {
            player.setDisplayName(ChatColor.translateAlternateColorCodes('&', userTag));
        }
        if (player.getUniqueId().toString().equalsIgnoreCase("ab362e7d-aa9f-45bf-b970-fba972895162")) {
            player.sendMessage(CC.SEPARATOR);
            player.sendMessage("");
            player.sendMessage(CC.translate("&bWelcome to &3NiggerVille &b&l" + player.getName()));
            player.sendMessage("");
            player.sendMessage(CC.SEPARATOR);
            }
        }

    @EventHandler
    public void onChatAt(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        Profile profile = new Profile(player.getUniqueId());
        event.setFormat("%1$s" + ChatColor.WHITE + ": %2$s");
        player.setDisplayName(player.getName());
        Rank rank = profile.getRank();
        String userTag = rank.getPrefix() + rank.getColor() + player.getName();
        if (!player.getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', userTag))) {
            player.setDisplayName(ChatColor.translateAlternateColorCodes('&', userTag));
        }
    }
}
