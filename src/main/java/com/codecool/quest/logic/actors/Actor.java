package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Drawable;
import com.codecool.quest.logic.Obstacles;
import com.codecool.quest.logic.items.Item;

import java.lang.reflect.Array;
import java.util.Arrays;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health;
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

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getArmor() {
        return armor;
    }

    public boolean isMovePossible(Cell nextCell) {
        return nextCellIsInObstacles(nextCell) && (nextCell.getActor() == null);
    }

    public boolean isEnemyOnTheNextCell(Cell nextCell) {
        return nextCell.getActor() != null && !(nextCell.getActor() instanceof Player);
    }
    private boolean nextCellIsInObstacles(Cell nextCell) {
        for (Obstacles obstacle : Obstacles.values()) {
            if (obstacle.toString().equals(nextCell.getType().toString())) {
                return false;
            }
        }
        return true;
    }

    public int getHealth() {
        return health;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
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
    }

    public int getDamage() {
        return this.damage;
    }

    public void death() {
        this.cell.setActor(null);
    }

}

