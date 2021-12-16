package abilities;

import olympian.OlympianType;

public class Ability {

    public enum AbilityType {
        ATTACK, NONATTACK
    }

    private String name;
    private OlympianType olympian;
    private AbilityType type;

    private float HP;
    private int cooldown;

    public Ability(String name, AbilityType type) {
        this.name = name;
        this.type = type;
    }

    public Ability setHP(float hp) {
        this.HP = hp;
        return this;
    }

    public Ability setCooldown(int cooldown) {
        this.cooldown = cooldown;
        return this;
    }

    public float getHP() {
        return this.HP;
    }

    public int getCooldown() {
        return this.cooldown;
    }

}