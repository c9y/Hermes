package cc.zeala.hermes.staff;

import cc.zeala.hermes.utils.ColorText;
import org.bukkit.entity.*;

import java.util.*;

public class AdminChatHandler {

    private static List<UUID> adminchatList;

    public static boolean isAdminChat(final Player player) {
        return AdminChatHandler.adminchatList.contains(player.getUniqueId());
    }

    public static void setAdminChat(final Player player, final boolean b) {
        if (b) {
            AdminChatHandler.adminchatList.add(player.getUniqueId());
            player.sendMessage(ColorText.translate("&7You have entered &cadmin &7chat."));
        }
        else {
            AdminChatHandler.adminchatList.remove(player.getUniqueId());
            player.sendMessage(ColorText.translate("&7You have left &cadmin &7chat."));
        }
    }

    static {
        AdminChatHandler.adminchatList = new ArrayList<UUID>();
    }
}
