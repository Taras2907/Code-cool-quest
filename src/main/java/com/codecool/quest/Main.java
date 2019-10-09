package com.codecool.quest;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Directions;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.MapLoader;
import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.actors.Killable;
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
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.LinkedList;


public class Main extends Application {
    private GameMap map = MapLoader.loadMap("/map.txt");
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
    ObservableList<String> items;
    TextArea battleLog = new TextArea("Player received 10 damage");
    TextArea itemDescription = new TextArea();
    ListView<String> lista = new ListView<>();



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
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
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        this.scene = scene;
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Codecool Quest");
        primaryStage.show();


        Task<Void> moveEnemies = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Player player = map.getPlayer();
                while (player.getHealth()>0) {
                    Thread.sleep(2000);

                    LinkedList<Actor> enemies = map.getEnemies();

                    for (Actor enemy : enemies) {

                        Thread thread = new Thread(() -> {
                            Cell enemyCell = enemy.getCell();
                            Cell nextCell;

                            Directions direction = Directions.randomDirection();
                            int dx = direction.getDx();
                            int dy = direction.getDy();

                            nextCell = enemyCell.getNeighbor(dx, dy);

                            while (!enemy.isMovePossible(nextCell)) {
                                direction = Directions.randomDirection();
                                dx = direction.getDx();
                                dy = direction.getDy();

                                nextCell = enemyCell.getNeighbor(dx, dy);
                            }

                            enemy.move(dx, dy);

                        });
                        thread.start();
                    }

                    refresh();
                }
                return null;
            }
        };

        new Thread(moveEnemies).start();
        scene.getRoot().requestFocus();
    }

    private void fillTheItemDescription(MouseEvent mouseEvent) {
        String itemName = lista.getSelectionModel().getSelectedItem();
        String itemDescription = map.getPlayer().getItemDescriptionByItemName(itemName);
        this.itemDescription.setText(itemDescription);
        scene.getRoot().requestFocus();
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
    private void changeButtonColorIfThereIsAnItemInCell(Cell playerCell){
        if (playerCell.getItem() != null){
            pickUpButton.setStyle("-fx-background-color: green;-fx-text-fill: white");
        }else {
            pickUpButton.setStyle("-fx-background-color: red;-fx-text-fill: white");
        }
    }
    private void changeMap(String filePath){
        //copy current player to new map
        this.player = map.getPlayer();
        items = player.getItems();
        int attack = player.getDamage();
        int armor = player.getArmor();
        int experience = this.player.getCurrentExperience();
        int level = this.player.getCurrentLevel();
        int maxHealth = this.player.getMaxHealth();
        int currentHealth = player.getHealth();
        this.map = MapLoader.loadMap(filePath);
        this.map.getPlayer().setCurrentExperience(experience);
        this.map.getPlayer().setMaxHealth(maxHealth);
        this.map.getPlayer().setCurrentLevel(level);
        this.map.getPlayer().setHealth(currentHealth);
        this.map.getPlayer().setItems(items);
        this.map.getPlayer().setDamage(attack);
        this.map.getPlayer().setArmor(armor);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        scene.getRoot().requestFocus();
        if (map.getPlayer() != null) {
            Player player = map.getPlayer();
            Cell playerCell = player.getCell();
            Cell nextCell;
            Actor enemy;
            int dx = 0;
            int dy = 0;

            String directionKey = keyEvent.getCode().toString();

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
            player.tryToOpenTheDoorIfThereIsAny(nextCell, player);
            if (nextCell.getType().equals(CellType.EXIT)){
                changeMap(map.getNextMap());
            }else if (nextCell.getType().equals(CellType.EXIT_WIN)){
                map = MapLoader.loadMap(map.getWinMap());
            }
            changeButtonColorIfThereIsAnItemInCell(nextCell);

            if (player.isMovePossible(nextCell)) {
                player.move(dx, dy);
            } else if (player.isEnemyOnTheNextCell(nextCell)) {
                enemy = nextCell.getActor();
                enemy.receiveDamage(player.getDamage());
                if (enemy.getHealth() < 0) {
                    enemy.death();
                    player.receiveExperience((Killable) enemy);
                    map.removeEnemyFromList(enemy);
                } else {
                    player.receiveDamage(enemy.getDamage());
                    if (player.getHealth() <= 0) {
                        refresh();
                        this.map = MapLoader.loadMap(map.getGameOverMap());
                    }
                }
            }
            refresh();
        }
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
