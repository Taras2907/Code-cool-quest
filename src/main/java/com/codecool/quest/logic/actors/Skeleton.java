package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.actors.Actor;

public class Skeleton extends Actor {

    public Skeleton(Cell cell) {
        super(cell);
        int damage = 2;
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
}
