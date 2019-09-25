package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Bandit extends Actor {
    public Bandit(Cell cell) {
        super(cell);
        int damage = 4;
        int startingHealth = 8;
        int armor = 0;
        this.setHealth(startingHealth);
        this.setDamage(damage);
        this.setArmor(armor);
    }

    @Override
    public String getTileName() {
        return "bandit";
    }
}
