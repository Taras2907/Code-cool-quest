package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;

public class Potion extends Item {
    private String tileName;
    public Potion(Cell cell, String tileName, String description) {
        super(cell, tileName, description);
        this.tileName = tileName;
    }

    @Override
    public String getTileName() {
        return tileName;
    }
}
