package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Necromancer extends Enemy implements Killable{
    private static final int experienceForKilling = 1000;
    public Necromancer(Cell cell, double movementPointsDecremental) {
        super(cell, movementPointsDecremental);
        int damage = 14;
        int startingHealth = 14;
        int armor = 2;
        this.setHealth(startingHealth);
        this.setDamage(damage);
        this.setArmor(armor);
    }

    @Override
    public String getTileName() {
        return "necromancer";
    }

    @Override
    public int getExperienceForKilling() {
        return experienceForKilling;
    }
}
