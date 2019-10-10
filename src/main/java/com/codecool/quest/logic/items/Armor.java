package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;

public class Armor extends Item {
    private int defence = 2;
    private String tileName;
    public Armor(Cell cell, String tileName, String description) {
        super(cell, tileName, description);
        this.tileName = tileName;
    }

    @Override
    public String getTileName() {
        return tileName;
    }

    public int getDefence() {
        return defence;
    }

//    public void equip(Player player) { player.increaseDefence(defence); }
}
