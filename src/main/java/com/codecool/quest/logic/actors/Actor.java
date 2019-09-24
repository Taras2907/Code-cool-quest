package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;
    private int damage;
    protected int armor;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (isMovePossible(nextCell)) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public boolean isMovePossible(Cell nextCell) {
        return nextCell.getType().equals(CellType.FLOOR) && (nextCell.getActor() == null);
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