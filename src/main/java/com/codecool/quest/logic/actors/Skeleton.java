package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Directions;
import com.codecool.quest.logic.Drawable;
import com.codecool.quest.logic.actors.Actor;

import java.util.*;

public class Skeleton extends Enemy implements Killable {
    private static final int experienceForKilling = 330;
    public Skeleton(Cell cell, double movementPointsDecremental) {
        super(cell, movementPointsDecremental);
        int damage = 8;
        int startingHealth = 6;
        int armor = 1;
        this.setHealth(startingHealth);
        this.setDamage(damage);
        this.setArmor(armor);
    }


    @Override
    public String getTileName() {
        return "skeleton";
    }


    @Override
    public int getExperienceForKilling() {
        return experienceForKilling;
    }
}
