package player;

import olympian.Olympian;

import java.util.UUID;

public class Player {

    private final UUID uuid;
    private final String IGN;
    private Olympian currentOlympian;

    public Player(String IGN) {
        this.IGN = IGN;
        uuid = UUID.randomUUID();
    }

    public Player setCurrentOlympian(Olympian olympian) {
        this.currentOlympian = currentOlympian;
        return this;
    }

    public UUID getPlayerUUID() {
        return uuid;
    }

    public String getIGN() {
        return IGN;
    }

    public Olympian getCurrentOlympian() {
        return this.currentOlympian;
    }

}