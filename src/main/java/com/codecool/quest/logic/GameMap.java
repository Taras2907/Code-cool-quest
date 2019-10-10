package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.actors.Enemy;
import com.codecool.quest.logic.actors.Player;

import java.util.LinkedList;

public class GameMap {
    private static int currentLevelIndex = 0;
    private String[] levels = new String[]{"/map.txt", "/map1.txt", "/end_game_win.txt"};
    private int width;
    private int height;
    private Cell[][] cells;
    private LinkedList<Enemy> enemies = new LinkedList<Enemy>();

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

    public void addEnemyToEnemiesList(Enemy enemy) {
        enemies.add(enemy);
    }

    public LinkedList<Enemy> getEnemies() {
        return enemies;
    }

    public void removeEnemyFromList(Actor enemy) {
        enemies.remove(enemy);
    }

    public String getNextMap(){
        currentLevelIndex++;
        return levels[currentLevelIndex];
    }

    public void makeEnemiesTurn() {
        for (Enemy enemy : enemies) {
            enemy.makeTurn();
        }
    }

    public GameMap changeMap(Player player1){
        //copy current player to new map
        return MapLoader.loadMap(getNextMap(), player1);

    }

    public String getGameOverMap(){
        return "/end_game_lose.txt";
    }
}
