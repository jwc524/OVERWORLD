package olympian;

public enum OlympianType {

    ZEUS("Zeus", 0),
    HERA("Hera", 1),
    POSEIDON("Poseidon", 2),
    DEMETER("Demeter", 3),
    ATHENA("Athena", 4),
    APOLLO("Apollo", 5),
    ARTEMIS("Artemis", 6),
    ARES("Ares", 7),
    HEPHAESTUS("Hephaestus", 8),
    APHRODITE("Aphrodite", 9),
    HERMES("Hermes", 10),
    DIONYSUS("Dionysus", 11);

    String NAME;
    int ID;

    OlympianType(String NAME, int ID) {
        this.NAME = NAME;
        this.ID = ID;
    }

}