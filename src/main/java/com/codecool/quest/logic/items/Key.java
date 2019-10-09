package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;

public class Key extends Item {
    private String color;
    private String tileName;
    public Key(Cell cell, String tileName, String description, String color) {
        super(cell, tileName, description);
        this.color = color;
        this.tileName = tileName;
    }
    public String getColor(){
        return this.color;
    }
    public String getTileName() {
        return tileName;
    }
}