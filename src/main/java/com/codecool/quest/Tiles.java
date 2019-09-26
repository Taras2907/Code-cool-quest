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
        tileMap.put("ghost", new Tile(26, 6));
        //items
        tileMap.put("blueKey", new Tile(17, 23));
        tileMap.put("goldenKey", new Tile(16, 23));
        tileMap.put("redKey", new Tile(18, 23));
        tileMap.put("armor", new Tile(4, 23));
        tileMap.put("weapon", new Tile(4, 24));
        tileMap.put("potion", new Tile(26, 23));
        //textures
        tileMap.put("blueDoor", new Tile(0, 10));
        tileMap.put("goldenDoor", new Tile(9, 11));
        tileMap.put("redDoor", new Tile(8, 11));
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

        tileMap.put("ALetter", new Tile(19, 30));
        tileMap.put("BLetter", new Tile(20, 30));
        tileMap.put("CLetter", new Tile(21, 30));
        tileMap.put("DLetter", new Tile(22, 30));
        tileMap.put("ELetter", new Tile(23, 30));
        tileMap.put("FLetter", new Tile(24, 30));
        tileMap.put("GLetter", new Tile(25, 30));
        tileMap.put("hLetter", new Tile(26, 30));
        tileMap.put("ILetter", new Tile(27, 30));
        tileMap.put("JLetter", new Tile(28, 30));
        tileMap.put("KLetter", new Tile(29, 30));
        tileMap.put("LLetter", new Tile(30, 30));
        tileMap.put("MLetter", new Tile(31, 30));
        tileMap.put("NLetter", new Tile(19, 31));
        tileMap.put("OLetter", new Tile(20, 31));
        tileMap.put("PLetter", new Tile(21, 31));
        tileMap.put("QLetter", new Tile(22, 31));
        tileMap.put("RLetter", new Tile(23, 31));
        tileMap.put("SLetter", new Tile(24, 31));
        tileMap.put("TLetter", new Tile(25, 31));
        tileMap.put("ULetter", new Tile(26, 31));
        tileMap.put("VLetter", new Tile(27, 31));
        tileMap.put("WLetter", new Tile(28, 31));
        tileMap.put("XLetter", new Tile(29, 31));
        tileMap.put("YLetter", new Tile(30, 31));
        tileMap.put("ZLetter", new Tile(31, 31));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
