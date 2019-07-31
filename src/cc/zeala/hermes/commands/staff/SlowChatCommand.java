package cc.zeala.hermes.commands.staff;

import cc.zeala.hermes.Hermes;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitTask;
import cc.zeala.hermes.utils.finalutil.StringUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SlowChatCommand implements CommandExecutor, Listener {

    private Hermes plugin;
    private long slowedTime;
    private BukkitTask task;
    private Map<String, Long> chatTimes;

    public SlowChatCommand(Hermes plugin) {
        this.plugin = plugin;
        this.slowedTime = 0L;
        this.chatTimes = new HashMap();

        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    private long convertToMillis(long time, TimeUnit unit) {
        return TimeUnit.MILLISECONDS.convert(time, unit);
    }

    public static String getTimeMessage(long time) {
        return DurationFormatUtils.formatDurationWords(time, true, true);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onChat(AsyncPlayerChatEvent event) {
        if (event.getPlayer().hasPermission("onu.slowchat.bypass")) {
            return;
        }
        if (this.slowedTime > 0L) {
            if (!this.chatTimes.containsKey(event.getPlayer().getName())) {
                this.chatTimes.put(event.getPlayer().getName(), Long.valueOf(System.currentTimeMillis() + this.slowedTime));
            } else {
                long timeRemaining = ((Long) this.chatTimes.get(event.getPlayer().getName())).longValue() - System.currentTimeMillis();
                if (timeRemaining / 1000L > 0L) {
                    event.setCancelled(true);

                    String timeMessage = getTimeMessage(timeRemaining);
                    event.getPlayer().sendMessage(ChatColor.RED + "Chat is currently slowed. You can speak in " + timeMessage + ".");
                } else {
                    this.chatTimes.put(event.getPlayer().getName(), Long.valueOf(System.currentTimeMillis() + this.slowedTime));
                }
            }
        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("onu.command.slowchat")) {
            sender.sendMessage(StringUtil.NO_PERMISSION);
            return true;
        }
        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /slowchat <seconds|off>");
            return true;
        }
        if (args[0].equalsIgnoreCase("off")) {
            if (this.slowedTime == 0L) {
                sender.sendMessage(ChatColor.RED + "Slowchat is not currently on.");
            } else {
                slowedTime = 0L;
                    if (this.task != null) {
                        this.task.cancel();
                        this.task = null;
                }
            }
        }
        return true;
    }

}
