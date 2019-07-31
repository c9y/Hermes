package cc.zeala.hermes.utils.command;

import cc.zeala.hermes.utils.ReflectionUtil;
import cc.zeala.hermes.utils.command.adapter.TypeAdapter;
import cc.zeala.hermes.utils.command.annotation.CommandInfo;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CommandHandler {

    private final Map<Class<?>, TypeAdapter> adapters = new HashMap<>();
    private final Set<BaseCommand> commands = new HashSet<>();
    private final CommandMap commandMap;

    public CommandHandler(JavaPlugin plugin) {
        this.commandMap = ReflectionUtil.getCommandMap(plugin.getServer());
    }

    public TypeAdapter getAdapter(Class<?> adapter) {
        return adapters.get(adapter);
    }

    public void registerTypeAdapter(Class<?> clazz, TypeAdapter adapter) {
        adapters.putIfAbsent(clazz, adapter);
    }

    public <T> void registerCommand(T object) {
        if (object.getClass().isAnnotationPresent(CommandInfo.class)) {
            BaseCommand command = new BaseCommand(this, object);
            commands.add(command);
            commandMap.register(command.getName(), command);
        }
    }

    public TypeAdapter getAdapterByAdapterClass(Class<? extends TypeAdapter> clazz) {
        return adapters.values().stream().filter(adapter -> adapter.getClass() == clazz).findFirst().orElse(null);
    }

}
