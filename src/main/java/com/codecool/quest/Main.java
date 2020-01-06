package com.codecool.quest;

import com.codecool.quest.logic.*;
import com.codecool.quest.logic.*;
import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Directions;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.MapLoader;
import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.actors.Player;
import com.codecool.quest.logic.items.Item;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;


public class Main extends Application {
    private boolean isGameRunning = true;

    public GameMap map = MapLoader.loadMap("/start_game.txt");
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();

    Label attackLabel = new Label();
    Label armorLabel = new Label();
    Button pickUpButton = new Button("pick up item");
    ProgressBar health = new ProgressBar(1);
    ProgressBar experience = new ProgressBar(0);
    Scene scene;
    Player player;
    Stage primaryStage;
    ObservableList<String> items;
    TextArea battleLog = new TextArea("Player received 10 damage");
    TextArea itemDescription = new TextArea();
    ListView<String> lista = new ListView<>();



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String whichScene = " ";
        this.primaryStage = primaryStage;
        setScene(whichScene);
        setPrimaryScene(this.primaryStage, whichScene);
    }

    private void setPrimaryScene(Stage primaryStage, String whichScene){
        primaryStage.setScene(scene);
        refresh();
        primaryStage.setTitle("Codecool Quest");
        primaryStage.show();

        if (whichScene.equals("gameScene")) {
            scene.setOnKeyPressed(this::onKeyPressed);
            scene.getRoot().requestFocus();
            new Thread(refreshScene).start();
            new Thread(controlEnemies).start();
        } else {
            scene.setOnKeyPressed(this::onSpacePressed);}
    }

    private Task<Void> refreshScene = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            while (isGameRunning) {
                int RENDER_TIME = 100;
                Thread.sleep(RENDER_TIME);
                refresh();
            }
            return null;
        }
    };

    private Task<Void> controlEnemies = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            while (isGameRunning) {
                int TURN_DURATION = 100;
                Thread.sleep(TURN_DURATION);
                map.makeEnemiesTurn();
            }
            return null;
        }
    };

    private void fillTheItemDescription(MouseEvent mouseEvent) {
        String itemName = lista.getSelectionModel().getSelectedItem();
        String itemDescription = map.getPlayer().getItemDescriptionByItemName(itemName);
        this.itemDescription.setText(itemDescription);
        scene.getRoot().requestFocus();
    }
        private void onSpacePressed(KeyEvent keyEvent){
            scene.getRoot().requestFocus();
            if (keyEvent.getCode() == KeyCode.SPACE) {
                this.map = MapLoader.loadMap("/map.txt");
                setScene("gameScene");
                setPrimaryScene(this.primaryStage, "gameScene");
                refresh();
            }
        }



    private void setScene(String whichScene){
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

            lista.setOnMouseClicked(this::fillTheItemDescription);

            pickUpButton.setOnAction(this::onPickUpButtonPressed);
            ui.add(new Label("Ass Level 1"), 0, 1);
            ui.add(new Label("Health"), 0, 2);
            health.setPrefWidth(160);
            health.setStyle("-fx-accent: red");
            ui.add(health, 0, 3);
            ui.add(new Label("Experience"), 0, 4);
            experience.setPrefWidth(160);
            experience.setStyle("-fx-accent: gray");
            ui.add(experience, 0, 5);
            ui.add(attackLabel, 0, 6);
            ui.add(armorLabel, 0, 7);
            ui.add(new Label("Inventory: "), 0, 8);
            ui.add(pickUpButton, 0, 9);
            lista.setPrefHeight(160);
            ui.add(lista, 0, 10);
            ui.add(new Label("Item description"), 0, 11);
            itemDescription.setStyle("-fx-text-overrun: elipsis");
            itemDescription.setWrapText(true);
            ui.add(itemDescription, 0, 12);
            ui.add(new Label("Battle log:"), 0, 14);
            battleLog.setPrefWidth(155);
            ui.add(battleLog, 0, 15);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        if (whichScene.equals("gameScene")){
            borderPane.setRight(ui);
        }

        this.scene = new Scene(borderPane);
    }

    private void onPickUpButtonPressed(ActionEvent actionEvent) {
        int playerX = map.getPlayer().getCell().getX();
        int playerY = map.getPlayer().getCell().getY();
        Item item = map.getCell(playerX, playerY).getItem();
        if (item != null) {
            map.getPlayer().addItemToInventory(item);
            lista.setItems(map.getPlayer().getItems());

            map.getCell(playerX, playerY).setItem(null);

        }
        scene.getRoot().requestFocus();
    }

    private void changeButtonColorIfThereIsAnItemInCell(Cell playerCell) {
        if (playerCell.getItem() != null) {
            pickUpButton.setStyle("-fx-background-color: green;-fx-text-fill: white");
        } else {
            pickUpButton.setStyle("-fx-background-color: red;-fx-text-fill: white");
        }
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        scene.getRoot().requestFocus();
        Player player = map.getPlayer();
        player.makeAction(keyEvent, this);
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                }else if (cell.getItem() != null){
                    Tiles.drawTile(context, cell.getItem(), x, y);
                }else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        if (map.getPlayer() != null){
            health.setProgress(map.getPlayer().healthBar());
            experience.setProgress(map.getPlayer().experienceBar());
            attackLabel.setText("Attack: " + map.getPlayer().getDamage());
            armorLabel.setText("Armor: " + map.getPlayer().getArmor());
        }
    }
}
