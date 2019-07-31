package me.kxda.hermes.ranks;

import me.kxda.hermes.utils.CC;
import org.bukkit.craftbukkit.libs.joptsimple.internal.Strings;

import java.util.Arrays;

public enum Rank {

    NORMAL("", CC.GREEN, "Normal"),
    VIP(CC.GRAY + "[" + CC.D_GREEN + "D" + CC.GRAY + "] ", CC.GREEN, "VIP"),
    MVP(CC.GRAY + "[" + CC.D_PURPLE + "S\u2605" + CC.GRAY + "] ", CC.PINK, "MVP"),
    HERO(CC.GRAY + "[" + CC.D_AQUA + "H\u2721" + CC.GRAY + "] ", CC.AQUA, "Hero"),
    BUILDER(CC.D_GREEN, "Builder"),
    YOUTUBE(CC.GOLD, "Media"),
    FAMOUS(CC.GOLD + CC.ITALIC,"Famous"),
    PARTNER(CC.LIGHT_PURPLE + CC.ITALIC,"Partner"),
    TMOD(CC.YELLOW, "T-Mod"),
    MOD(CC.D_AQUA , "Moderator"),
    SRMOD(CC.D_AQUA + CC.ITALIC, "Sr-Mod"),
    ADMIN(CC.DARK_RED, "Admin"),
    PLATADMIN(CC.DARK_RED + CC.ITALIC ,"Plat-Admin"),
    MANAGER(CC.GOLD, "Manager"),
    DEVELOPER(CC.AQUA + CC.ITALIC , "Developer"),
    OWNER(CC.DARK_PURPLE, "Owner");

    public static final Rank[] RANKS;
    private final String prefix;
    private final String color;
    private final String name;
    private int id;

    private Rank(final String prefix, final String color, final String name) {
        this.prefix = prefix;
        this.color = color;
        this.name = name;
        this.id = id;
    }

    private Rank(final String color, final String name) {
        this(CC.GRAY + "[" + color + name + CC.GRAY + "] ", color, name);
    }

    public static Rank getByName(final String name) {
        return Arrays.stream(Rank.RANKS).filter( rank -> rank.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public String getName() {
        final StringBuilder builder = new StringBuilder();
        final String split = this.toString().replaceAll("_", " ");
        for (final String s : split.split(" ")) {
            final String[] name = s.toLowerCase().split("");
            name[0] = name[0].toUpperCase();
            builder.append(Strings.join(name, "")).append("-");
        }
        return builder.substring(0, builder.length() - 1);
    }

    public int getPriority() {
        return this.ordinal();
    }

    public boolean hasRank(final Rank requiredRank) {
        return this.getPriority() >= requiredRank.getPriority();
    }

    public boolean isStaff() {
        return this.ordinal() >= Rank.TMOD.ordinal();
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getInfo() {
        return " §e- " + this.color + this.name + "§e, (" + this.getPrefix() + "§e" + this.getPriority() + "§e)";
    }

    public String getColor() {
        return this.color;
    }

    public int getId() {
        return this.id;
    }

    public boolean isBelowOrEqual(Rank rank) {
        return this.ordinal() <= rank.ordinal();
    }

    public boolean isAboveOrEqual(Rank rank) {
        return this.ordinal() >= rank.ordinal();
    }

    public static Rank getRankOrDefault(final int i) {
        Rank rank;
        if (i == 0) {
            rank = Rank.NORMAL;
        }
        else if (i == 3) {
            rank = Rank.VIP;
        }
        else if (i == 4) {
            rank = Rank.MVP;
        }
        else if (i == 5) {
            rank = Rank.HERO;
        }
        else if (i == 6) {
            rank = Rank.BUILDER;
        }
        else if (i == 7) {
            rank = Rank.YOUTUBE;
        }
        else if (i == 8) {
            rank = Rank.FAMOUS;
        }
        else if (i == 8) {
            rank = Rank.PARTNER;
        }
        else if (i == 9) {
            rank = Rank.TMOD;
        }
        else if (i == 10) {
            rank = Rank.MOD;
        }
        else if (i == 11) {
            rank = Rank.SRMOD;
        }
        else if (i == 11) {
            rank = Rank.ADMIN;
        }
        else if (i == 11) {
            rank = Rank.PLATADMIN;
        }
        else if (i == 13) {
            rank = Rank.MANAGER;
        }
        else if (i == 14) {
            rank = Rank.OWNER;
        }
        else if (i == 15) {
            rank = Rank.DEVELOPER;
        }
        else {
            rank = Rank.NORMAL;
        }
        return rank;
    }

    static {
        RANKS = values();
    }
}
