package com.codecool.quest.logic.Doors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;

public class Door implements Drawable {
    private String color;
    private Cell cell;
    public Door(Cell cell, String color){
        this.color = color;
        this.cell = cell;
        cell.setDoor(this);
    }

    public String getColor() {
        return color;
    }

    @Override
    public String getTileName() {
        return color +  "Door";
    }
}
