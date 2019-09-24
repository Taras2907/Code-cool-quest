package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Drawable;

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

    public void checkDeath(){
        if (health < 1){
            cell.setActor(null);
        }
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
        return nextCell.getType().equals(CellType.FLOOR) && (nextCell.getActor() == null);
    }

    public boolean isEnemyOnTheNextCell(Cell nextcell) {
        return nextcell.getActor() != null;
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
