package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Bandit extends Actor implements Killable {
    private static final int experienceForKilling = 400;
    public Bandit(Cell cell) {
        super(cell);
        int damage = 12;
        int startingHealth = 12;
        int armor = 1;
        this.setHealth(startingHealth);
        this.setDamage(damage);
        this.setArmor(armor);
    }

    @Override
    public String getTileName() {
        return "bandit";
    }

    @Override
    public int getExperienceForKilling() {
        return experienceForKilling;
    }
}
