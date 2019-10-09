package com.codecool.quest.logic;

import com.codecool.quest.logic.Doors.Door;
import com.codecool.quest.logic.actors.*;
import com.codecool.quest.logic.items.Armor;
import com.codecool.quest.logic.items.Key;
import com.codecool.quest.logic.items.Potion;
import com.codecool.quest.logic.items.Weapon;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {

    public static GameMap loadMap(String filePath) {

        InputStream is = MapLoader.class.getResourceAsStream(filePath);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            map.addEnemyToEnemiesList(new Skeleton(cell));
                            break;
                        case 'U':
                            cell.setType(CellType.CHURCH5);
                            new Ghost(cell);
                            break;
                        case 'B':
                            cell.setType(CellType.FLOOR);
                            new Bandit(cell);
                            break;
                        case 'n':
                            cell.setType(CellType.FLOOR);
                            new Necromancer(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key(cell, "blue Key", "Blue key was created to open blue doors and keep his owner in a good humor","blue");
                            break;
                        case 'г':
                            cell.setType(CellType.FLOOR);
                            new Key(cell, "golden Key", "Golden key obviously made from gold and it is very valuable because it opens the golden gates, do not try to sell it because you will became a target of a powerful curse","golden");
                            break;
                        case 'й':
                            cell.setType(CellType.FLOOR);
                            new Key(cell, "red Key", "Red key made from copper and it is very easy to carry this kind of keys on your neck, some heroes are trying to show off, but it is not a golden key, you will not get ladies attention", "red");
                            break;
                        case 'd':
                            cell.setType(CellType.DOOR);
                            new Door(cell,"blue");
                            break;
                        case 'D':
                            cell.setType(CellType.STEELDOOR);
                            new Door(cell,"red");
                            break;
                        case 'c':
                            cell.setType(CellType.CAGEDOOR);
                            new Door(cell,"golden");
                            break;
                        case 'a':
                            cell.setType(CellType.FLOOR);
                            new Armor(cell, "Bone Armor", "Armor is not the best choice, bone armor is made from ribs, so it helps you only with stupid skeletons(they have no idea that may stab you between ribs and armor won't help you)");
                            break;
                        case 'b':
                            cell.setType(CellType.BRIDGE);
                            break;
                        case 'z':
                            cell.setType(CellType.SIGN1);
                            break;
                        case 'g':
                            cell.setType(CellType.GRAVE1);
                            break;
                        case 'G':
                            cell.setType(CellType.GRAVE2);
                            break;
                        case 't':
                            cell.setType(CellType.TREE1);
                            break;
                        case 'T':
                            cell.setType(CellType.TREE2);
                            break;
                        case '|':
                            cell.setType(CellType.TREE3);
                            break;
                        case 'f':
                            cell.setType(CellType.FIRE);
                            break;
                        case 'r':
                            cell.setType(CellType.RIVER1);
                            break;
                        case 'R':
                            cell.setType(CellType.RIVER2);
                            break;
                        case '0': //pond
                            cell.setType(CellType.RIVER3);
                            break;
                            // church
                        case '1': //pond
                            cell.setType(CellType.CHURCH1);
                            break;
                        case '2':
                            cell.setType(CellType.CHURCH2);
                            break;
                        case '3':
                            cell.setType(CellType.CHURCH3);
                            break;
                        case '4':
                            cell.setType(CellType.CHURCH4);
                            break;
                        case '5':
                            cell.setType(CellType.CHURCH5);
                            break;
                        case '6':
                            cell.setType(CellType.CHURCH6);
                            break;
                        case '7':
                            cell.setType(CellType.CHURCH7);
                            break;
                        case '8':
                            cell.setType(CellType.CHURCH8);
                            break;
                        case '9':
                            cell.setType(CellType.CHURCH9);
                            break;
                        case 'e':
                            cell.setType(CellType.EXIT);
                            break;
                        case 'X':
                            cell.setType(CellType.EXIT_WIN);
                            break;
                        case '+':
                            cell.setType(CellType.CROSS1);
                            break;
                        case '=':
                            cell.setType(CellType.CROSS2);
                            break;
                        case 'w':
                            cell.setType(CellType.FLOOR);
                            new Weapon(cell, "Scythe of Priest", "Scythe of Priest not powerful and not magic but it is very long and not sharp so usually heroes scratching their backs and destroying skeletons ");
                            break;
                        case 'p':
                            cell.setType(CellType.FLOOR);
                            new Potion(cell, "Health Potion", "Made by main Boss grandmother to help heroes defeat him. Restores 10 health. (Main boss didn't visit his grandma for a while. Remember to visit your grandma or give her a call from time to time, because she could start help your rivals!)");
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'E':
                            cell.setType(CellType.YLETTER);
                            break;
                        case 'N':
                            cell.setType(CellType.OLETTER);
                            break;
                        case 'Q':
                            cell.setType(CellType.ULETTER);
                            break;
                        case 'L':
                            cell.setType(CellType.LLETTER);
                            break;
                        case 'Y':
                            cell.setType(CellType.ALETTER);
                            break;
                        case 'S':
                            cell.setType(CellType.SLETTER);
                            break;
                        case 'O':
                            cell.setType(CellType.ELETTER);
                            break;
                        case 'W':
                            cell.setType(CellType.WLETTER);
                            break;
                        case 'I':
                            cell.setType(CellType.ILETTER);
                            break;
                        case 'V':
                            cell.setType(CellType.NLETTER);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
