package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Doors.Door;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.MapLoader;
import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.items.Item;
import com.codecool.quest.logic.items.Key;
import com.codecool.quest.newMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

//import java.awt.event.KeyEvent;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Player extends Actor {
    ObservableList<String> items = FXCollections.observableArrayList ();
    ArrayList<Item> inventory = new ArrayList<>();
    private final int maxPlayerLevel = 5;
    private int currentLevel = 1;
    private int maxHealth = 40;
    private int currentExperience = 0;
    private int experienceForNewLevel = 1000;



    public Player(Cell cell) {
        super(cell);
        int damage = 5;
        int armor = 1;
        this.setHealth(maxHealth);
        this.setDamage(damage);
        this.setArmor(armor);
    }

    @Override
    public void makeTurn() {

    }

    public String getTileName() {
        return "player";
    }

    public void addItemToInventory(Item item){
        items.add(item.getTileName());
        inventory.add(item);
    }
    public boolean playerHasKeyForDoor(Door door){
        for (Item item : inventory){
            if(item instanceof Key){
                Key key = (Key) item;
                if (key.getColor().equals(door.getColor())){
                    items.remove(key.getTileName());
                    inventory.remove(key);
                    return true;
                }
            }
        }
        return  false;
    }

    public ObservableList<String> getItems() {
        return items;
    }

    public void setItems(ObservableList<String> items) {
        this.items = items;
    }
    public double healthBar(){
        return (double) this.getHealth() / 40;
    }
    public double experienceBar(){
        return (double) currentExperience / experienceForNewLevel;
    }
    public void tryToOpenTheDoorIfThereIsAny(Cell cell, Player player){
        if (cell.getDoor() != null){
            if( player.playerHasKeyForDoor(cell.getDoor())){
                cell.setType(CellType.FLOOR);
            }
        }
    }

    public void receiveExperience(Killable enemy){
        this.currentExperience += enemy.getExperienceForKilling();
        if (currentExperience >= experienceForNewLevel){
            receiveNewLevel();
        }
    }
    private void setExperienceForNewLevel(){
        this.experienceForNewLevel = currentLevel * 1000;
    }
    private void receiveNewLevel(){
        this.currentExperience = 0;
        this.maxHealth += 10;
        this.currentLevel += 1;
        this.setHealth(this.maxHealth);
        setExperienceForNewLevel();
        this.setDamage(this.getDamage() + 1);
        this.setArmor(this.getArmor() + 1);

    }

    public int getCurrentExperience() {
        return currentExperience;
    }
    public int getCurrentLevel(){
        return this.currentLevel;
    }
    public int getMaxHealth(){
        return this.maxHealth;
    }
    public void setCurrentLevel(int level){
        this.currentLevel = level;
    }
    public void setMaxHealth(int maxHealth){
        this.maxHealth = maxHealth;
    }

    public void setCurrentExperience(int currentExperience) {
        this.currentExperience = currentExperience;
    }


    public void makeAction(KeyEvent keyEvent, newMain main) {
        GameMap map = main.map;
        Cell playerCell = getCell();
        Cell nextCell;
        Actor enemy;
        int dx = 0;
        int dy = 0;
        switch (keyEvent.getCode()) {
            case UP:
                dx = 0;
                dy = -1;
                break;
            case DOWN:
                dx = 0;
                dy = 1;
                break;
            case LEFT:
                dx = -1;
                dy = 0;
                break;
            case RIGHT:
                dx = 1;
                dy = 0;
                break;
            default:
                break;
        }
        nextCell = playerCell.getNeighbor(dx, dy);
        tryToOpenTheDoorIfThereIsAny(nextCell, this);
        if (nextCell.getType().equals(CellType.EXIT)){
            main.setMap(map.changeMap(this));
        }
//        changeButtonColorIfThereIsAnItemInCell(nextCell);

        if (isMovePossible(nextCell)) {
            move(dx, dy);
        } else if (isEnemyOnTheNextCell(nextCell)) {
            enemy = nextCell.getActor();
            enemy.receiveDamage(getDamage());
            if (enemy.getHealth() < 0) {
                enemy.death();
                receiveExperience((Killable) enemy);
                map.removeEnemyFromList(enemy);
            } else {
                receiveDamage(enemy.getDamage());
                if (getHealth() <= 0) {
                    map = MapLoader.loadMap(map.getGameOverMap());
                }
            }
        }
    }
}