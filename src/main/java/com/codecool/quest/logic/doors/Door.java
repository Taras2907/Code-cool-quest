package com.codecool.quest.logic.doors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;

public class Door implements Drawable {
    private Cell cell;
    public Door(Cell cell){
        this.cell = cell;
    }

    @Override
    public String getTileName() {
        return "door";
    }
}
