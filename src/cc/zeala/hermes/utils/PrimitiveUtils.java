package cc.zeala.hermes.utils;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class PrimitiveUtils {

    public static final Map<String, Boolean> POSSIBLE_BOOLEAN_COMBINATIONS = new HashMap<>();
    public static final Map<String, Long> TIME_TO_MILLIS_COMBINATIONS = new HashMap<>();

    static {
        POSSIBLE_BOOLEAN_COMBINATIONS.put("yes", true);
        POSSIBLE_BOOLEAN_COMBINATIONS.put("no", false);
        POSSIBLE_BOOLEAN_COMBINATIONS.put("true", true);
        POSSIBLE_BOOLEAN_COMBINATIONS.put("false", false);
        POSSIBLE_BOOLEAN_COMBINATIONS.put("1", true);
        POSSIBLE_BOOLEAN_COMBINATIONS.put("0", false);

        TIME_TO_MILLIS_COMBINATIONS.put("y", TimeUnit.DAYS.toMillis(1) * 365);
        TIME_TO_MILLIS_COMBINATIONS.put("years", TimeUnit.DAYS.toMillis(1) * 365);

        TIME_TO_MILLIS_COMBINATIONS.put("mm", TimeUnit.DAYS.toMillis(1) * 30);
        TIME_TO_MILLIS_COMBINATIONS.put("months", TimeUnit.DAYS.toMillis(1) * 30);

        TIME_TO_MILLIS_COMBINATIONS.put("d", TimeUnit.DAYS.toMillis(1));
        TIME_TO_MILLIS_COMBINATIONS.put("days", TimeUnit.DAYS.toMillis(1));

        TIME_TO_MILLIS_COMBINATIONS.put("h", TimeUnit.HOURS.toMillis(1));
        TIME_TO_MILLIS_COMBINATIONS.put("hours", TimeUnit.HOURS.toMillis(1));

        TIME_TO_MILLIS_COMBINATIONS.put("m", TimeUnit.MINUTES.toMillis(1));
        TIME_TO_MILLIS_COMBINATIONS.put("minutes", TimeUnit.MINUTES.toMillis(1));

        TIME_TO_MILLIS_COMBINATIONS.put("s", TimeUnit.SECONDS.toSeconds(1));
        TIME_TO_MILLIS_COMBINATIONS.put("seconds", TimeUnit.SECONDS.toSeconds(1));

    }

    public static Optional<Boolean> parseBoolean(String string) {
        if (!POSSIBLE_BOOLEAN_COMBINATIONS.containsKey(string.toLowerCase()))
            return Optional.empty();
        return Optional.of(POSSIBLE_BOOLEAN_COMBINATIONS.get(string.toLowerCase()));
    }

    public static String findCloseTimeKeyword(String s) {
        return TIME_TO_MILLIS_COMBINATIONS.keySet().stream().filter(str -> str.contains(s)).findFirst().orElse(null);
    }

    public static String removeNumbersFromString(String s) {
        if (isNumber(s)) return "";
        List<Character> filtered = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (!isNumber(c + ""))
                filtered.add(c);
        }
        Character[] characters = filtered.toArray(new Character[0]);
        StringBuilder builder = new StringBuilder();
        for (Character c : characters)
            builder.append(c);

        return builder.toString();
    }

    public static boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String convertToBestTime(long durationInMillis) {

        long millisInSeconds = TimeUnit.MILLISECONDS.toSeconds(durationInMillis);

        long remainder = millisInSeconds % 86400;

        long days = millisInSeconds / TimeUnit.DAYS.toSeconds(1);
        long hours = remainder / TimeUnit.HOURS.toSeconds(1);
        long minutes = (remainder / 60) - (hours * 60);
        long seconds = (remainder % TimeUnit.HOURS.toSeconds(1)) - (minutes * 60);

        StringBuilder builder = new StringBuilder();
        if (days > 0)
            builder.append(days).append(" days");

        if (hours > 0)
            builder.append(" ").append(hours).append(" hours");

        if (minutes > 0)
            builder.append(" ").append(minutes).append(" minutes");

        if (seconds > 0)
            builder.append(" ").append(seconds).append(" seconds");

        return builder.toString();
    }

    public static Long getTimeFromString(String s) {
        long time = 0;
        int lastIndex = 0;
        int lastLetterIndex = 0;
        for (Map.Entry<String, Long> entry : TIME_TO_MILLIS_COMBINATIONS.entrySet()) {
            if (s.indexOf(entry.getKey()) != -1) {
                lastLetterIndex = s.indexOf(entry.getKey());
                time = Long.parseLong(s.substring(lastIndex, lastLetterIndex)) * entry.getValue();
                lastIndex += 1;
            }
        }
        return time;
    }

}

