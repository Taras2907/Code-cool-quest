package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Ghost extends Actor implements Killable{
    private static final int experienceForKilling = 500;
    public Ghost(Cell cell) {
        super(cell);
        int damage = 20;
        int startingHealth = 16;
        int armor = 1;
        this.setHealth(startingHealth);
        this.setDamage(damage);
        this.setArmor(armor);
    }

    @Override
    public void makeTurn() {

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
