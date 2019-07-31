package cc.zeala.hermes.staff.freeze.managers;

import cc.zeala.hermes.Hermes;
import cc.zeala.hermes.staff.freeze.Manager;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FrozenManager extends Manager
{
    private Set<UUID> frozenUUIDs;
    
    public FrozenManager(final Hermes plugin) {
        super(plugin);
        this.frozenUUIDs = new HashSet<UUID>();
    }
    
    public void freezeUUID(final UUID uuid) {
        this.frozenUUIDs.add(uuid);
    }
    
    public void unfreezeUUID(final UUID uuid) {
        this.frozenUUIDs.remove(uuid);
    }
    
    public boolean isFrozen(final UUID uuid) {
        return this.frozenUUIDs.contains(uuid);
    }
}
