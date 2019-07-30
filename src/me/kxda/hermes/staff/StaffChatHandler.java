package me.kxda.hermes.staff;

import me.kxda.hermes.utils.ColorText;
import org.bukkit.entity.*;

import java.util.*;

public class StaffChatHandler {

    private static List<UUID> staffchatList;

    public static boolean isStaffChat(final Player player) {
        return StaffChatHandler.staffchatList.contains(player.getUniqueId());
    }

    public static void setStaffChat(final Player player, final boolean b) {
        if (b) {
            StaffChatHandler.staffchatList.add(player.getUniqueId());
            player.sendMessage(ColorText.translate("&7You have entered &bstaff &7chat."));
        }
        else {
            StaffChatHandler.staffchatList.remove(player.getUniqueId());
            player.sendMessage(ColorText.translate("&7You have left &bstaff &7chat."));
        }
    }

    static {
        StaffChatHandler.staffchatList = new ArrayList<UUID>();
    }
}
