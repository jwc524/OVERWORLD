package abilities;

import olympian.OLYMPIANTYPE;

public class Ability {

    private String name;
    private OLYMPIANTYPE olympianType;
    private ABILITYTYPE abilityType;

    private ABILITYLEVEL level;
    private float levelMultiplier;
    private float HP;
    private int cooldown;

    public Ability THUNDERSTRIKE = new THUNDERSTRIKE().setHP(5.0f).setCooldown(10);

    public Ability(String name, ABILITYTYPE abilitytype, OLYMPIANTYPE olympianType) {
        this.name = name;
        this.abilityType = abilitytype;
        this.olympianType = olympianType;

        this.level = ABILITYLEVEL.ONE;
    }

    /** Sets HP value of ability. **/
    public Ability setHP(float hp) {
        this.HP = hp;
        return this;
    }

    /** Sets cooldown for ability in seconds. **/
    public Ability setCooldown(int cooldown) {
        this.cooldown = cooldown;
        return this;
    }

    public Ability upgradeLevel() {
        if (this.level != ABILITYLEVEL.FIVE) {
            level = ABILITYLEVEL.getNextLevel(this.level);
        }
        return this;
    }

    public Ability setLevelMultiplier(float multiplier) {
        this.levelMultiplier = multiplier;
        return this;
    }

    public float getHP() {
        return this.HP;
    }

    public int getCooldown() {
        return this.cooldown;
    }

    public ABILITYLEVEL getLevel() {
        return this.level;
    }

    public float getLevelMultiplier(ABILITYLEVEL level) {
        return (level.ordinal() + 1) * levelMultiplier;
    }

}