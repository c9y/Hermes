package me.kxda.hermes.punishments;

import lombok.Getter;

public enum PunishmentType {

    BANNED("Banned"),
    MUTE("Muted"),
    TEMPBAN("TempBan"),
    BLACKLISTED("Blacklisted"),
    BITCH("Bitch"),
    KICK("Kicked");

    @Getter
    private String context;

    private PunishmentType(final String context) {
        this.context = context;
    }

    public String getContext() {
        return this.context;
    }
}
