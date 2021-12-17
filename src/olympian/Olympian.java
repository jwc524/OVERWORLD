package olympian;

import abilities.Ability;

public class Olympian {

    protected String name;
    protected OLYMPIANTYPE TYPE;

    protected float MAX_HEALTH;
    protected float REGENERATION_SPEED;

    protected float SLOW_SPEED;
    protected float WALK_SPEED;
    protected float SPRINT_SPEED;

    protected boolean blocking = false;
    protected boolean sprinting = false;

    protected Ability L1_ABILITY;
    protected Ability L2_ABILITY;
    protected Ability ULTIMATE;

    public Olympian ZEUS = new Zeus("Zeus").setMaxHealth(100).setSlowSpeed(0.5f).setWalkSpeed(1.0f).setSprintSpeed(2.0f).setRegenerationSpeed(5.0f).setL1Ability(Ability.THUNDERSTRIKE);

    public Olympian(String name) {
        this.name = name;
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

    public Olympian setL1Ability(Ability ability) {
        this.L1_ABILITY = ability;
        return this;
    }

    public Olympian setL2Ability(Ability ability) {
        this.L2_ABILITY = ability;
        return this;
    }

    public Olympian setUltimate(Ability ultimate) {
        this.ULTIMATE = ultimate;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public OLYMPIANTYPE getOlympianType() {
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

    public Ability getL1Ability() {
        return this.L1_ABILITY;
    }

    public Ability getL2Ability() {
        return this.L2_ABILITY;
    }

    public Ability setUltimate() {
        return this.ULTIMATE;
    }

}