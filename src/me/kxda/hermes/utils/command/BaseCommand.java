package me.kxda.hermes.utils.command;

import me.kxda.hermes.utils.command.adapter.TypeAdapter;
import me.kxda.hermes.utils.command.annotation.CommandInfo;
import me.kxda.hermes.utils.command.annotation.Default;
import me.kxda.hermes.utils.command.annotation.ParameterType;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import me.kxda.hermes.utils.TranslateUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BaseCommand extends Command {
    private final CommandHandler commandHandler;
    private final Object command;
    private final CommandInfo info;
    private final Method executeMethod;
    private final Map<CommandInfo, Method> subCommands;

    protected BaseCommand(final CommandHandler handler, final Object command) {
        super(command.getClass().getAnnotation(CommandInfo.class).name());
        this.info = command.getClass().getAnnotation(CommandInfo.class);
        this.commandHandler = handler;
        this.command = command;
        this.setAliases((List)Arrays.asList(this.info.aliases()));
        this.subCommands = CommandUtil.getInfoToMethodMapping(command);
        this.executeMethod = Arrays.stream(command.getClass().getMethods()).filter(method -> method.isAnnotationPresent(Default.class)).findFirst().orElse(null);
    }

    public List<String> tabComplete(final CommandSender sender, final String alias, final String[] args) throws IllegalArgumentException {
        if (args.length == 0) {
            if (this.executeMethod.getParameters().length < 1) {
                return new ArrayList<String>();
            }
            if (this.executeMethod.getParameters()[1].getType() != null) {
                final Parameter parameter = this.executeMethod.getParameters()[1];
                TypeAdapter adapter;
                if (parameter.isAnnotationPresent(ParameterType.class)) {
                    adapter = this.commandHandler.getAdapterByAdapterClass(parameter.getAnnotation(ParameterType.class).value());
                }
                else {
                    adapter = this.commandHandler.getAdapter(parameter.getType());
                }
                if (adapter != null) {
                    return (List<String>)adapter.tabComplete(args, "");
                }
            }
        }
        if (args.length > 0) {
            final String firstArgument = args[0];
            final String s = null;
            if (this.subCommands.keySet().stream().anyMatch(info -> info.name().equalsIgnoreCase(s) || ArrayUtils.contains((Object[])info.aliases(), (Object)s.toLowerCase()))) {
                final String s2 = null;
                final Method method = this.subCommands.get(this.subCommands.keySet().stream().filter(info -> info.name().equalsIgnoreCase(s2) || ArrayUtils.contains((Object[])info.aliases(), (Object)s2.toLowerCase())).findFirst().orElse(null));
                if (method.getParameters().length < 1) {
                    return new ArrayList<String>();
                }
                if (method.getParameters().length < args.length - 1) {
                    return new ArrayList<String>();
                }
                final Parameter parameter2 = method.getParameters()[args.length - 1];
                TypeAdapter adapter2;
                if (parameter2.isAnnotationPresent(ParameterType.class)) {
                    adapter2 = this.commandHandler.getAdapter(parameter2.getAnnotation(ParameterType.class).value());
                }
                else {
                    adapter2 = this.commandHandler.getAdapter(parameter2.getType());
                }
                if (adapter2 != null) {
                    return (List<String>)adapter2.tabComplete(args, args[args.length - 1]);
                }
            }
        }
        return new ArrayList<String>();
    }

    public boolean execute(final CommandSender sender, final String commandLabel, final String[] args) {
        if (args.length == 0) {
            if (this.executeMethod == null) {
                sender.sendMessage(TranslateUtil.translate(this.info.usage()));
                return true;
            }
            try {
                CommandUtil.executeCommand(this.commandHandler, sender, this.command, this.info, this.executeMethod, args);
            }
            catch (InvocationTargetException | IllegalAccessException ex4) {
                final ReflectiveOperationException ex = new ReflectiveOperationException();
                final ReflectiveOperationException e = ex;
                e.printStackTrace();
            }
            return true;
        }
        else {
            if (args.length > 0) {
                for (final Map.Entry<CommandInfo, Method> entry : this.subCommands.entrySet()) {
                    final CommandInfo info = entry.getKey();
                    if (!ArrayUtils.contains((Object[])info.aliases(), (Object)args[0])) {
                        if (!args[0].equalsIgnoreCase(info.name())) {
                            continue;
                        }
                    }
                    try {
                        CommandUtil.executeCommand(this.commandHandler, sender, this.command, info, entry.getValue(), Arrays.copyOfRange(args, 1, args.length));
                        return true;
                    }
                    catch (InvocationTargetException | IllegalAccessException ex5) {
                        final ReflectiveOperationException ex2 = new ReflectiveOperationException();
                        final ReflectiveOperationException e2 = ex2;
                        e2.printStackTrace();
                        return true;
                    }
                }
                try {
                    CommandUtil.executeCommand(this.commandHandler, sender, this.command, this.info, this.executeMethod, args);
                }
                catch (InvocationTargetException | IllegalAccessException ex6) {
                    final ReflectiveOperationException ex3 = new ReflectiveOperationException();
                    final ReflectiveOperationException e = ex3;
                    sender.sendMessage(TranslateUtil.translate(this.info.usage()));
                }
                return true;
            }
            return true;
        }
    }
}

