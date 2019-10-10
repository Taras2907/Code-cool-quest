package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Directions;
import com.codecool.quest.logic.Drawable;
import com.codecool.quest.logic.actors.Actor;

import java.util.*;

public class Skeleton extends Actor implements Killable {
    private static final int experienceForKilling = 330;
    public Skeleton(Cell cell) {
        super(cell);
        int damage = 8;
        int startingHealth = 6;
        int armor = 1;
        this.setHealth(startingHealth);
        this.setDamage(damage);
        this.setArmor(armor);
    }

    public void makeTurn() {
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


//    }
//
//    public void makeTurn() {
//        Cell enemyCell = getCell();
//        Cell nextCell;
//
//        Directions direction = Directions.randomDirection();
//        int dx = direction.getDx();
//        int dy = direction.getDy();
//
//        nextCell = enemyCell.getNeighbor(dx, dy);
//
//        List<Directions> possibleDirections = Arrays.asList(Directions.values());
//
//        while (!isMovePossible(nextCell)) {
//            direction = Directions.randomDirection();
//            dx = direction.getDx();
//            dy = direction.getDy();
//
//            nextCell = enemyCell.getNeighbor(dx, dy);
//        }
//
//        move(dx, dy);
//    }

    @Override
    public String getTileName() {
        return "skeleton";
    }


    @Override
    public int getExperienceForKilling() {
        return experienceForKilling;
    }
}
