package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Ghost extends Actor {
    public Ghost(Cell cell) {
        super(cell);
        int damage = 4;
        int startingHealth = 8;
        int armor = 1;
        this.setHealth(startingHealth);
        this.setDamage(damage);
        this.setArmor(armor);
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}
