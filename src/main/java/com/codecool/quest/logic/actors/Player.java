package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.actors.Actor;

public class Player extends Actor {

    public Player(Cell cell) {
        super(cell);
        int damage = 5;
        int startingHealth = 10;
        int armor = 1;
        this.setHealth(startingHealth);
        this.setDamage(damage);
        this.setArmor(armor);
    }

    public String getTileName() {
        return "player";
    }
}
