package cc.zeala.hermes.utils;

import cc.zeala.hermes.utils.command.CommandHandler;
import cc.zeala.hermes.utils.command.adapter.*;
import cc.zeala.hermes.utils.command.adapter.*;
import org.bukkit.entity.Player;

public class CommandUtil {

    public static void registerImportantTypeAdapters(CommandHandler commandHandler) {
        commandHandler.registerTypeAdapter(boolean.class, new BooleanTypeAdapter());
        commandHandler.registerTypeAdapter(double.class, new DoubleTypeAdapter());
        commandHandler.registerTypeAdapter(int.class, new IntegerTypeAdapter());
        commandHandler.registerTypeAdapter(Integer.class, new IntegerTypeAdapter());
        commandHandler.registerTypeAdapter(Double.class, new DoubleTypeAdapter());
        commandHandler.registerTypeAdapter(Boolean.class, new BooleanTypeAdapter());
        commandHandler.registerTypeAdapter(Player.class, new PlayerTypeAdapter());
        commandHandler.registerTypeAdapter(String.class, new StringTypeAdapter());
    }

}
