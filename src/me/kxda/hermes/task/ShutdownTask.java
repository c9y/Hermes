package me.kxda.hermes.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.kxda.hermes.Hermes;
import me.kxda.hermes.utils.CC;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ShutdownTask extends BukkitRunnable
{
    private final List<Integer> BROADCAST_TIMES;
    private Hermes plugin;
    private int secondsUntilShutdown;

    public void run() {
        if (this.BROADCAST_TIMES.contains(this.secondsUntilShutdown)) {
            this.plugin.getServer().broadcastMessage(CC.LIGHT_PURPLE + "(Valor) " + CC.LIGHT_PURPLE + "The command will shutdown in " + CC.GRAY + this.secondsUntilShutdown + CC.LIGHT_PURPLE + " seconds.");
        }
        if (this.secondsUntilShutdown <= 0) {
            this.plugin.getServer().getOnlinePlayers().forEach(player -> player.sendMessage(CC.RED + "The command has shut down."));
            this.plugin.getServer().shutdown();
        }
        --this.secondsUntilShutdown;
    }

    public List<Integer> getBROADCAST_TIMES() {
        return this.BROADCAST_TIMES;
    }

    public Hermes getPlugin() {
        return this.plugin;
    }

    public int getSecondsUntilShutdown() {
        return this.secondsUntilShutdown;
    }

    public void setPlugin(final Hermes plugin) {
        this.plugin = plugin;
    }

    public void setSecondsUntilShutdown(final int secondsUntilShutdown) {
        this.secondsUntilShutdown = secondsUntilShutdown;
    }

    public ShutdownTask(final Hermes plugin, final int secondsUntilShutdown) {
        this.BROADCAST_TIMES = Arrays.asList(3600, 1800, 900, 600, 300, 180, 120, 60, 45, 30, 15, 10, 5, 4, 3, 2, 1);
        this.plugin = plugin;
        this.secondsUntilShutdown = secondsUntilShutdown;
    }
}
