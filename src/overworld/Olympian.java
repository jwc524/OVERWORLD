package overworld;

public class Olympian {

    private String name;
    private final OlympianType TYPE;

    private float health;
    private float speed;
    private float regenerationSpeed;

    public Olympian(String name, OlympianType type) {
        this.name = name;
        this.TYPE = type;
    }

    public Olympian setHealth(float health) {
        this.health = health;
        return this;
    }

    public Olympian setSpeed(float speed) {
        this.speed = speed;
        return this;
    }

    public Olympian setRegenerationSpeed(float regenSpeed) {
        this.regenerationSpeed = regenSpeed;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public OlympianType getOlympianType() {
        return TYPE;
    }

    public float getHealth() {
        return health;
    }

    public float getSpeed() {
        return speed;
    }

    public float getRegenerationSpeed() {
        return regenerationSpeed;
    }

}