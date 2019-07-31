package cc.zeala.hermes.staff.freeze;

import cc.zeala.hermes.staff.freeze.managers.FrozenManager;
import cc.zeala.hermes.staff.freeze.managers.InventoryManager;
import cc.zeala.hermes.staff.freeze.managers.PlayerSnapshotManager;
import cc.zeala.hermes.Hermes;

public class ManagerHandler
{
    private Hermes plugin;
    private InventoryManager inventoryManager;
    private FrozenManager frozenManager;
    private PlayerSnapshotManager playerSnapshotManager;

    public ManagerHandler(final Hermes plugin) {
        this.plugin = plugin;
        this.loadManagers();
    }

    private void loadManagers() {
        this.inventoryManager = new InventoryManager(this.plugin);
        this.frozenManager = new FrozenManager(this.plugin);
        this.playerSnapshotManager = new PlayerSnapshotManager(this.plugin);
    }

    public InventoryManager getInventoryManager() {
        return this.inventoryManager;
    }

    public FrozenManager getFrozenManager() {
        return this.frozenManager;
    }

    public PlayerSnapshotManager getPlayerSnapshotManager() {
        return this.playerSnapshotManager;
    }
}

