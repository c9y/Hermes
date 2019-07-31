package me.kxda.hermes.utils;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandMap;
import org.bukkit.entity.Player;
import org.joor.Reflect;

public class ReflectionUtil {

    private static final String VERSION = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
    private static final String CB_PACKAGE = "org.bukkit.craftbukkit." + VERSION + ".";
    private static final String NMS_PACKAGE = "net.minecraft.server." + VERSION + ".";

    public static int getPing(Player player) {
        return (int) Reflect.on(player).call("getHandle").get("ping");
    }

    public static CommandMap getCommandMap(Server server) {
        return (CommandMap) Reflect.on(server).get("commandMap");
    }

}
