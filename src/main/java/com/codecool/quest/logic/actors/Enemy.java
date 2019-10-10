package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Directions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public abstract class Enemy extends Actor {
    private double movementPoints;
    private double movementPointsDecremental;
    public Enemy(Cell cell, double movementPointsDecremental) {
        super(cell);
        movementPoints = 10;
        this.movementPointsDecremental = movementPointsDecremental;
    }

    private void makeMovement() {
        Cell enemyCell = getCell();
        Cell nextCell;

        ArrayList<Directions> possibleDirections = new ArrayList<>(Arrays.asList(Directions.values()));
        Random random = new Random();

        while (possibleDirections.size() > 0) {
            int randomIndex = random.nextInt(possibleDirections.size());
            Directions direction = possibleDirections.get(randomIndex);
            int dx = direction.getDx();
            int dy = direction.getDy();

            nextCell = enemyCell.getNeighbor(dx, dy);

            if (isMovePossible(nextCell)) {
                move(dx, dy);
                break;
            } else {
                possibleDirections.remove(direction);
            }
        }
    }

    public void makeTurn() {
        if (movementPoints > 0) {
            movementPoints -= movementPointsDecremental;
        } else {
            makeMovement();
            movementPoints = 10;
        }
    }

}
