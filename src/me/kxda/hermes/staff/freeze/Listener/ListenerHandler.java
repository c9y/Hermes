package me.kxda.hermes.staff.freeze.Listener;

import me.kxda.hermes.staff.freeze.Listener.listeners.EntityListener;
import me.kxda.hermes.staff.freeze.Listener.listeners.InventoryListener;
import me.kxda.hermes.Hermes;
import me.kxda.hermes.staff.freeze.Listener.listeners.PlayerListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class ListenerHandler
{
    private Hermes plugin;
    private Listener[] listeners;
    
    public ListenerHandler(final Hermes plugin) {
        this.listeners = new Listener[] { new EntityListener(this), new InventoryListener(this), new PlayerListener (this) };
        this.plugin = plugin;
        this.loadListeners();
    }
    
    private void loadListeners() {
        Listener[] arrayOfListener;
        for (int j = (arrayOfListener = this.listeners).length, i = 0; i < j; ++i) {
            final Listener listener = arrayOfListener[i];
            this.plugin.getServer().getPluginManager().registerEvents(listener, (Plugin)this.plugin);
        }
    }
    
    public Hermes getPlugin() {
        return this.plugin;
    }
}
