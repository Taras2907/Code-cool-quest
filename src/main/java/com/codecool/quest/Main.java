package com.codecool.quest;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.MapLoader;
import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.actors.Player;
import com.codecool.quest.logic.actors.Skeleton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.border.Border;

public class Main extends Application {
    private GameMap map = MapLoader.loadMap("/map1.txt");
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Button pickUpButton = new Button("pick up item");
    Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        pickUpButton.setOnAction(this::onPickUpButtonPressed);
        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(pickUpButton, 1, 2);

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
        scene.getRoot().requestFocus();
    }

    private void onPickUpButtonPressed(ActionEvent actionEvent){
        int playerX = map.getPlayer().getCell().getX();
        int playerY = map.getPlayer().getCell().getY();
        map.getCell(playerX, playerY).setItem(null);

        scene.getRoot().requestFocus();
    }
    private void changeButtonColorIfThereIsAnItemInCell(Cell playerCell){
        if (playerCell.getItem() != null){
            pickUpButton.setStyle("-fx-background-color: green");
        }else {
            pickUpButton.setStyle("-fx-background-color: red");
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        Player player = map.getPlayer();
        Cell playerCell = player.getCell();

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
        }
        nextCell = playerCell.getNeighbor(dx, dy);
        changeButtonColorIfThereIsAnItemInCell(nextCell);
        System.out.println(nextCell.getTileName());

        if (player.isMovePossible(nextCell)) {
            player.move(dx, dy);
        } else if (player.isEnemyOnTheNextCell(nextCell)) {
            enemy = nextCell.getActor();
            enemy.setHealth(enemy.getHealth() - player.getDamage());
            player.setHealth(player.getHealth() - enemy.getDamage());
            enemy.checkDeath();
            player.checkDeath();
            System.out.println("Enemy damage 5p");
        }
        refresh();
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
        healthLabel.setText("" + map.getPlayer().getHealth());
    }
}
