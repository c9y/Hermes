package me.kxda.hermes;

import lombok.Getter;
import lombok.Setter;
import me.kxda.hermes.commands.admin.*;
import me.kxda.hermes.commands.player.*;
import me.kxda.hermes.commands.staff.ClearChatCommand;
import me.kxda.hermes.commands.staff.FreezeCommand;
import me.kxda.hermes.commands.staff.MuteChatCommand;
import me.kxda.hermes.commands.staff.StaffChatCommand;
import me.kxda.hermes.profile.ProfileListener;
import me.kxda.hermes.manager.PlayerManager;
import me.kxda.hermes.punishments.command.*;
import me.kxda.hermes.staff.freeze.Listener.ListenerHandler;
import me.kxda.hermes.staff.freeze.ManagerHandler;
import me.kxda.hermes.task.ShutdownTask;
import me.kxda.hermes.utils.VanishListener;
import me.kxda.hermes.utils.finalutil.PlayerEvents;
import me.kxda.hermes.database.Database;
import me.kxda.hermes.punishments.PunishmentManager;
import me.kxda.hermes.punishments.listener.PunishmentListener;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class Hermes extends JavaPlugin implements Listener {

    private Database Database;
    @Getter
    private PlayerManager playerManager;
    @Getter
    private static Hermes instance;
    @Setter
    private Location spawnLocation;
    @Getter
    private PunishmentManager punishmentManager;
    @Setter
    private ShutdownTask shutdownTask = null;
    @Getter
    public ArrayList<Player> staffchat;
    @Getter
    public List<String> messages;
    @Getter
    private ManagerHandler managerHandler;
    @Getter
    public Random rn;
    @Getter
    public List<Player> nomsg;
    @Getter
    public List<Player> rail;
    @Getter
    public HashMap<String, String> reply;
    @Getter
    public HashMap<Player, Integer> chatdelay;
    @Getter
    public ArrayList<Player> adminchat;


    public static Hermes getInstance() {
        return Hermes.instance;
    }


    @Override
    public void onEnable() {

        instance = this;
        super.onEnable();

        this.database();

        this.managers();

        this.listeners();

        this.commands();

        this.registerEvents();

        this.staffchat = new ArrayList<Player>();
        this.adminchat = new ArrayList<Player>();
        this.messages = new ArrayList<String>();
        this.managerHandler = new ManagerHandler(this);
        this.rn = new Random();
        this.nomsg = new ArrayList<Player>();
        this.rail = new ArrayList<Player>();
        this.reply = new HashMap<String, String>();
        this.chatdelay = new HashMap<Player, Integer>();
        new ListenerHandler(this);

    }

    private void database() {
        Database.openConnection();
        Database.createTables();
    }


    public void registerEvents() {
        final PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerEvents(), this);
    }

    private void managers() {
        this.punishmentManager = new PunishmentManager(this);
    }

    private void listeners() {
        this.getServer().getPluginManager().registerEvents(new ProfileListener(), this);
        this.getServer().getPluginManager().registerEvents(new PunishmentListener(this.punishmentManager), this);
    }

    private void registerCommand(final Command cmd) {
        this.registerCommand(cmd, this.getName());
    }

    public void registerCommand(final Command cmd, final String fallbackPrefix) {
        MinecraftServer.getServer().server.getCommandMap().register(cmd.getName(), fallbackPrefix, cmd);
    }

    private void commands() {
        this.getCommand("hermes").setExecutor(new HermesCommand());
        this.getCommand("setrank").setExecutor(new SetRankCommand());
        this.getCommand("clearchat").setExecutor(new ClearChatCommand(this));
        this.getCommand("teamspeak").setExecutor(new TeamSpeakCommand());
        this.getCommand("discord").setExecutor(new DiscordCommand());
        this.getCommand("mutechat").setExecutor(new MuteChatCommand());
        this.getCommand("adminchat").setExecutor(new AdminChatCommand());
        this.getCommand("staffchat").setExecutor(new StaffChatCommand());
        this.getCommand("broadcast").setExecutor(new BroadcastCommand());
        this.getCommand("report").setExecutor(new ReportCommand());
        this.getCommand("freeze").setExecutor(new FreezeCommand(this));
        this.getCommand("msg").setExecutor(new MessageCommand(this));
        this.getCommand("r").setExecutor(new ReplyCommand(this));
        this.getCommand("tpm").setExecutor(new TPMCommand(this));
        this.getCommand("ranks").setExecutor(new RanksCommand());
        this.getCommand("eat").setExecutor(new EatCommand());
        this.getCommand("heal").setExecutor(new HealCommand());
        this.getCommand("gms").setExecutor(new SurvivalCommand());
        this.getCommand("gmc").setExecutor(new CreativeCommand());
        this.getCommand("gma").setExecutor(new AdventureCommand());
        this.getCommand("teleport").setExecutor(new TeleportCommand());

        new BanCommand(this.punishmentManager);
        new BlacklistCommand(this.punishmentManager);
        new UnbanCommand(this.punishmentManager);
        new HistoryCommand(this.punishmentManager);
        new KickCommand(this.punishmentManager);
        new MuteCommand(this.punishmentManager);
        new UnmuteCommand(this.punishmentManager);
        new TempBanCommand(this.punishmentManager);
        new TempMuteCommand(this.punishmentManager);
        new ShutdownCommand(this);
        new UnblacklistCommand(this.punishmentManager);
    }

    @Override
    public void onDisable() {
        Database.closeConnection();
        this.punishmentManager.cancel();
        this.punishmentManager.save();
    }

    public static Hermes getPlugin() {
        return (Hermes) JavaPlugin.getPlugin(Hermes.class);
    }

    public PlayerManager getPlayerManager() {
        return this.playerManager;
    }


    public ManagerHandler getManagerHandler() {
        return this.managerHandler;
    }
    {
}

public VanishListener getVanishListner() {
    return null;
  }
}


