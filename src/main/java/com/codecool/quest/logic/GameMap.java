package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.actors.Player;

import java.util.LinkedList;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;
    private LinkedList<Actor> enemies = new LinkedList<Actor>();

    private Player player;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addEnemyToEnemiesList(Actor enemy) {
        enemies.add(enemy);
    }

    public LinkedList<Actor> getEnemies() {
        return enemies;
    }
}
