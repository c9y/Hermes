package cc.zeala.hermes.staff.freeze.managers;

import cc.zeala.hermes.Hermes;
import cc.zeala.hermes.staff.freeze.Manager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager extends Manager
{
    private Inventory frozenInv;
    
    public InventoryManager(final Hermes plugin) {
        super(plugin);
        this.initiateFrozenInv();
    }
    
    private void initiateFrozenInv() {
        this.frozenInv = this.Plugin.getServer().createInventory((InventoryHolder)null, 9, "ScreenShare");
        final ItemStack paper = new ItemStack(Material.PAPER);
        final ItemMeta itemMeta = paper.getItemMeta();
        final List<String> lores = new ArrayList<String>();
        lores.add(0, ChatColor.LIGHT_PURPLE + "Don't logout you have 3 minutes.");
        lores.add(1, ChatColor.DARK_PURPLE + "Press ALT + Tab to minimize your game.");
        itemMeta.setLore((List)lores);
        itemMeta.setDisplayName(ChatColor.GRAY + "TS: ts.valorpvp.club");
        paper.setItemMeta(itemMeta);
        this.frozenInv.setItem(4, paper);
    }
    
    public Inventory getFrozenInv() {
        return this.frozenInv;
    }
}
