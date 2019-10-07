package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.actors.Actor;

public class Skeleton extends Actor implements Killable {
    private static final int experienceForKilling = 400;
    public Skeleton(Cell cell) {
        super(cell);
        int damage = 8;
        int startingHealth = 10;
        int armor = 1;
        this.setHealth(startingHealth);
        this.setDamage(damage);
        this.setArmor(armor);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }


    @Override
    public int getExperienceForKilling() {
        return experienceForKilling;
    }
}
