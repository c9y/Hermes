package cc.zeala.hermes.utils;

import com.google.common.collect.Lists;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TranslateUtil {


    public static final String LINE = "--------------------------------";
    private static final char ALTERNATE_COLOR_CHAR = '&';

    public static String translate(String input) {
        return ChatColor.translateAlternateColorCodes(ALTERNATE_COLOR_CHAR, input);
    }

    public static Collection<String> translateCollection(Collection<String> collection) {
        List<String> list = Lists.newArrayList(collection);
        list.replaceAll(TranslateUtil::translate);
        return list;
    }

    public static String[] translate(String... inputs) {
        return translateCollection(Arrays.asList(inputs)).toArray(new String[0]);
    }

    public static String getRankDisplay(String display) {
        System.out.println(ChatColor.stripColor("[" + translate(display) + "]"));
        if (display.isEmpty())
            return "None";
        else if (ChatColor.stripColor(translate(display)).equals("")) {
            return translate(display) + display;
        } else {
            return translate(display);
        }
    }

    /*public static String formatPunishment(Long duration, String reason) {
        String display = Settings.PunishmentSection.banFormat.stream().reduce((previous, next) -> previous.concat("\n").concat(next)).get();
        display = display.replace("%reason%", reason).replace("%time%", PrimitiveUtils.convertToBestTime(duration));
        return display;
    }*/

}
