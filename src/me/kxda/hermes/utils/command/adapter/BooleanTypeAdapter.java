package me.kxda.hermes.utils.command.adapter;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import me.kxda.hermes.utils.PrimitiveUtils;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BooleanTypeAdapter implements TypeAdapter<Boolean> {

    @Override
    public Optional<Boolean> parse(Parameter[] parameters, String[] args, Parameter currentParameter, String currentArgument) {
        return PrimitiveUtils.parseBoolean(currentArgument);
    }

    @Override
    public void onError(CommandSender executor, String argument) {
        executor.sendMessage(ChatColor.RED + "Expecting a yes/no value.");
    }

    @Override
    public List<String> tabComplete(String[] args, String currentArgument) {
        return PrimitiveUtils.POSSIBLE_BOOLEAN_COMBINATIONS
                .keySet()
                .stream()
                .filter(string -> currentArgument.startsWith(string.toLowerCase()))
                .collect(Collectors.toList());
    }
}
