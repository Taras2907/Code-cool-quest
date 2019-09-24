package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;

public class Armor extends Item {
    private int defence = 2;
    public Armor(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "armor";
    }

    public int getDefence() {
        return defence;
    }
}
