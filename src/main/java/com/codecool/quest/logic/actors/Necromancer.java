package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Necromancer extends Actor {
    public Necromancer(Cell cell) {
        super(cell);
        int damage = 4;
        int startingHealth = 12;
        int armor = 2;
        this.setHealth(startingHealth);
        this.setDamage(damage);
        this.setArmor(armor);
    }

    @Override
    public String getTileName() {
        return "necromancer";
    }
}
