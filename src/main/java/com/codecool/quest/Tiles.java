package com.codecool.quest;

import com.codecool.quest.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();

    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        //actors
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("necromancer", new Tile(27, 2));
        tileMap.put("bandit", new Tile(25, 1));
        //items
        tileMap.put("key", new Tile(17, 23));
        tileMap.put("armor", new Tile(4, 23));
        tileMap.put("weapon", new Tile(4, 24));
        tileMap.put("potion", new Tile(26, 23));
        //textures
        tileMap.put("door", new Tile(0, 10));
        tileMap.put("steelDoor", new Tile(9, 11));
        tileMap.put("cageDoor", new Tile(8, 11));
        //graves
        tileMap.put("grave1", new Tile(1, 14));
        tileMap.put("grave2", new Tile(0, 14));
        //trees
        tileMap.put("tree1", new Tile(6, 2));
        tileMap.put("tree2", new Tile(5, 1));
        tileMap.put("tree3", new Tile(5, 1));
        //river and bridge
        tileMap.put("bridge", new Tile(6, 5 ));
        tileMap.put("river1", new Tile(8, 4));
        tileMap.put("river2", new Tile(9, 4));
        tileMap.put("river3", new Tile(8, 5));
        //signs
        tileMap.put("sign1", new Tile(0, 7));
        //fire
        tileMap.put("fire", new Tile(14, 10));
        //Church
        tileMap.put("church1", new Tile(18, 0)); // |-
        tileMap.put("church2", new Tile(19, 0)); //  -
        tileMap.put("church3", new Tile(20, 0)); // -|
        tileMap.put("church4", new Tile(18, 1)); // |
        tileMap.put("church5", new Tile(19, 1));
        tileMap.put("church6", new Tile(20, 1));
        tileMap.put("church7", new Tile(18, 2));
        tileMap.put("church8", new Tile(19, 2));
        tileMap.put("church9", new Tile(20, 2));
        //cross
        tileMap.put("cross1", new Tile(25, 12));
        tileMap.put("cross2", new Tile(25, 13));
        //exit
        tileMap.put("exit", new Tile(23, 2));


    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
