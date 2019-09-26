package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;

public class Key extends Item {
    private String color;
    private Cell cell;
    public Key(Cell cell, String color) {
        super(cell);
        this.color = color;
    }
    public String getColor(){
        return this.color;
    }
    public String getTileName() {
        return color + " Key";
    }
}