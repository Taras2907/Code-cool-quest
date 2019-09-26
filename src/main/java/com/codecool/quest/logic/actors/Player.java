package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Doors.Door;
import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.items.Item;
import com.codecool.quest.logic.items.Key;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class Player extends Actor {

    //ListView<Item> inventory = new ListView<>();
    ArrayList<Item> inventory = new ArrayList<>();

    public Player(Cell cell) {
        super(cell);
        int damage = 5;
        int startingHealth = 10;
        int armor = 1;
        this.setHealth(startingHealth);
        this.setDamage(damage);
        this.setArmor(armor);
    }

    public String getTileName() {
        return "player";
    }

    public void addItemToInventory(Item item){

        inventory.add(item);
    }
    public boolean playerHasKeyForDoor(Door door){
        for (Item item : inventory){
            if(item instanceof Key){
                Key key = (Key) item;
                if (key.getColor().equals(door.getColor())){
                    return true;
                }
            }
        }
        return  false;
    }
}
