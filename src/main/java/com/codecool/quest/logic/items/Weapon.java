package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;

public class Weapon extends Item {
    public Weapon(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "weapon";
    }
}
