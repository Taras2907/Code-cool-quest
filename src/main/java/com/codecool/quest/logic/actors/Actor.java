package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;
import com.codecool.quest.logic.items.Item;

import java.lang.reflect.Array;
import java.util.Arrays;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;
    private int damage;
    private int armor;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

    public int getHealth() {
        return health;
    }


    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public void receiveDamage(int enemyDamage) {
        this.health -= enemyDamage/ armor;
        if (this.health <= 0) {
            this.death();
        }
    }

    public int getDamage() {
        return this.damage;
    }

    private void death() {
        this.cell.setActor(null);
    }
}
