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
                            new Skeleton(cell);
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
                            new Key(cell, "blue");
                            break;
                        case 'г':
                            cell.setType(CellType.FLOOR);
                            new Key(cell, "golden");
                            break;
                        case 'й':
                            cell.setType(CellType.FLOOR);
                            new Key(cell, "red");
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
                            new Armor(cell);
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
                        case '+':
                            cell.setType(CellType.CROSS1);
                            break;
                        case '=':
                            cell.setType(CellType.CROSS2);
                            break;
                        case 'w':
                            cell.setType(CellType.FLOOR);
                            new Weapon(cell);
                            break;
                        case 'p':
                            cell.setType(CellType.FLOOR);
                            new Potion(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
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
