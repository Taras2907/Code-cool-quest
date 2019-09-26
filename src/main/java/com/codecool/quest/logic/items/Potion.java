package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;

public class Potion extends Item {
    public Potion(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "potion";
    }
}
