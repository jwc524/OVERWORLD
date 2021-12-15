package olympian;

public class Olympian {

    private String name;
    private final OlympianType TYPE;

    private float MAX_HEALTH;
    private float REGENERATION_SPEED;

    private float SLOW_SPEED;
    private float WALK_SPEED;
    private float SPRINT_SPEED;

    private boolean blocking = false;
    private boolean sprinting = false;

    public Olympian(String name, OlympianType TYPE) {
        this.name = name;
        this.TYPE = TYPE;
    }

    public Olympian setMaxHealth(float MAX_HEALTH) {
        this.MAX_HEALTH = MAX_HEALTH;
        return this;
    }

    public Olympian setSlowSpeed(float SLOW_SPEED) {
        this.SLOW_SPEED = SLOW_SPEED;
        return this;
    }

    public Olympian setWalkSpeed(float WALK_SPEED) {
        this.WALK_SPEED = WALK_SPEED;
        return this;
    }

    public Olympian setSprintSpeed(float SPRINT_SPEED) {
        this.SPRINT_SPEED = SPRINT_SPEED;
        return this;
    }

    public Olympian setRegenerationSpeed(float regenSpeed) {
        this.REGENERATION_SPEED = regenSpeed;
        return this;
    }

    public void setBlocking(boolean blocking) {
        this.blocking = blocking;
    }

    public void setSprinting(boolean sprinting) {
        this.sprinting = sprinting;
    }

    public String getName() {
        return this.name;
    }

    public OlympianType getOlympianType() {
        return TYPE;
    }

    public float getMaxHealth() {
        return MAX_HEALTH;
    }

    public float getSlowSpeed() {
        return SLOW_SPEED;
    }

    public float getWalkSpeed() {
        return WALK_SPEED;
    }

    public float getSprintSpeed() {
        return SPRINT_SPEED;
    }

    public float getRegenerationSpeed() {
        return REGENERATION_SPEED;
    }

    public boolean isBlocking() {
        return this.blocking;
    }

    public boolean isSprinting() {
        return this.sprinting;
    }

}