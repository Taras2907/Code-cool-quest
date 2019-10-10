package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Ghost extends Enemy implements Killable{
    private static final int experienceForKilling = 500;
    public Ghost(Cell cell, double movementPointsDecremental) {
        super(cell, movementPointsDecremental);
        int damage = 20;
        int startingHealth = 16;
        int armor = 1;
        this.setHealth(startingHealth);
        this.setDamage(damage);
        this.setArmor(armor);
    }



    @Override
    public int getExperienceForKilling() {
        return experienceForKilling;
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}
