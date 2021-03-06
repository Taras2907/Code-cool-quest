//package com.codecool.quest;
//
//import com.codecool.quest.logic.*;
//import com.codecool.quest.logic.actors.Actor;
//import com.codecool.quest.logic.actors.Killable;
//import com.codecool.quest.logic.actors.Player;
//import com.codecool.quest.logic.items.Item;
//import javafx.application.Application;
//import javafx.collections.ObservableList;
//import javafx.concurrent.Task;
//import javafx.event.ActionEvent;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.canvas.Canvas;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ListView;
//import javafx.scene.control.ProgressBar;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.GridPane;
//import javafx.scene.paint.Color;
//import javafx.stage.Stage;
//
//import java.util.LinkedList;
//
//
//public class newMain extends Application {
//    private boolean isGameRunning = true;
//
//    public GameMap map = MapLoader.loadMap("/map.txt");
//    Canvas canvas = new Canvas(
//            map.getWidth() * Tiles.TILE_WIDTH,
//            map.getHeight() * Tiles.TILE_WIDTH);
//    GraphicsContext context = canvas.getGraphicsContext2D();
//
//    Label attackLabel = new Label();
//    Label armorLabel = new Label();
//    Button pickUpButton = new Button("pick up item");
//    ProgressBar health = new ProgressBar(1);
//    ProgressBar experience = new ProgressBar(0);
//    Scene scene;
//    Player player;
//    ObservableList<String> items;
//    ListView<String> lista = new ListView<String >();
////    ImageView imageView = new ImageView();
//
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        GridPane ui = new GridPane();
//        ui.setPrefWidth(200);
//        ui.setPadding(new Insets(10));
//
//        pickUpButton.setOnAction(this::onPickUpButtonPressed);
//        ui.add(new Label("Health"), 0, 1);
//        health.setPrefWidth(160);
//        health.setStyle("-fx-accent: red");
//        ui.add(health, 0, 2);
//        ui.add(new Label("Experience"), 0, 3);
//        experience.setPrefWidth(160);
//        experience.setStyle("-fx-accent: gray");
//        ui.add(experience, 0, 4);
//        ui.add(attackLabel, 0, 5);
//        ui.add(armorLabel, 0, 6);
//        ui.add(new Label("Inventory: "), 0, 7);
//        ui.add(pickUpButton, 0, 8);
//        ui.add(lista, 0, 9);
//
//
//        BorderPane borderPane = new BorderPane();
//
//        borderPane.setCenter(canvas);
//        borderPane.setRight(ui);
//
//        Scene scene = new Scene(borderPane);
//        this.scene = scene;
//        primaryStage.setScene(scene);
//
//        refresh();
//
//        scene.setOnKeyPressed(this::onKeyPressed);
//
//        primaryStage.setTitle("Codecool Quest");
//        primaryStage.show();
//
//        scene.getRoot().requestFocus();
//        new Thread(refreshScene).start();
//        new Thread(controlEnemies).start();
//    }
//
//    private Task<Void> refreshScene = new Task<Void>() {
//        @Override
//        protected Void call() throws Exception {
//            while (isGameRunning) {
//                int RENDER_TIME = 100;
//                Thread.sleep(RENDER_TIME);
//                refresh();
//            }
//            return null;
//        }
//    };
//
//    private Task<Void> controlEnemies = new Task<Void>() {
//        @Override
//        protected Void call() throws Exception {
//            while (isGameRunning) {
//                int TURN_DURATION = 1000;
//                Thread.sleep(TURN_DURATION);
//                map.makeEnemiesTurn();
//            }
//            return null;
//        }
//    };
//
//    private void onPickUpButtonPressed(ActionEvent actionEvent) {
//        int playerX = map.getPlayer().getCell().getX();
//        int playerY = map.getPlayer().getCell().getY();
//        Item item = map.getCell(playerX, playerY).getItem();
//        if (item != null) {
//            map.getPlayer().addItemToInventory(item);
//            lista.setItems(map.getPlayer().getItems());
//
//            map.getCell(playerX, playerY).setItem(null);
//
//        }
//        scene.getRoot().requestFocus();
//    }
//    private void changeButtonColorIfThereIsAnItemInCell(Cell playerCell){
//        if (playerCell.getItem() != null){
//            pickUpButton.setStyle("-fx-background-color: green;-fx-text-fill: white");
//        }else {
//            pickUpButton.setStyle("-fx-background-color: red;-fx-text-fill: white");
//        }
//    }
//
//    public void setMap(GameMap map) {
//        this.map = map;
//    }
//
//    private void onKeyPressed(KeyEvent keyEvent) {
//        scene.getRoot().requestFocus();
//        Player player = map.getPlayer();
//        player.makeAction(keyEvent, this);
//    }
//
//    private void refresh() {
//        context.setFill(Color.BLACK);
//        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
//        for (int x = 0; x < map.getWidth(); x++) {
//            for (int y = 0; y < map.getHeight(); y++) {
//                Cell cell = map.getCell(x, y);
//                if (cell.getActor() != null) {
//                    Tiles.drawTile(context, cell.getActor(), x, y);
//                }else if (cell.getItem() != null){
//                    Tiles.drawTile(context, cell.getItem(), x, y);
//                }else {
//                    Tiles.drawTile(context, cell, x, y);
//                }
//            }
//        }
//        if (map.getPlayer() != null){
//            health.setProgress(map.getPlayer().healthBar());
//            experience.setProgress(map.getPlayer().experienceBar());
//            attackLabel.setText("Attack: " + map.getPlayer().getDamage());
//            armorLabel.setText("Armor: " + map.getPlayer().getArmor());
//        }
//    }
//}
