package me.kxda.hermes.utils.command.adapter;

import org.bukkit.command.CommandSender;

import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Optional;

public interface TypeAdapter<V> {

    Optional<V> parse(Parameter[] parameters, String[] args, Parameter currentParameter, String currentArgument);

    void onError(CommandSender executor, String argument);

    List<String> tabComplete(String[] args, String currentArgument);

}
