package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;

public class Weapon extends Item {
    private String tileName;
    public Weapon(Cell cell, String tileName, String description) {
        super(cell, tileName, description);
        this.tileName = tileName;
    }

    @Override
    public String getTileName() {
        return tileName;
    }
}
