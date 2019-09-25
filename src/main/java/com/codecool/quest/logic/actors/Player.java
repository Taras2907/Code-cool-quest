package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.items.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.Arrays;

public class Player extends Actor {
    ListView<Item> inventory = new ListView<>();
    ObservableList<String> item = FXCollections.observableArrayList();

    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }

    public void addItemToInventory(Item item){
        inventory.getItems().add(item);
    }
}
