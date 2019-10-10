package com.codecool.quest.logic;

public enum CellType {
    //basic
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    //textures
    DOOR("blueDoor"),
    STEELDOOR("redDoor"),
    CAGEDOOR("goldenDoor"),

    RIVER1("river1"),
    RIVER2("river2"),
    RIVER3("river3"),

    BRIDGE("bridge"),

    TREE1("tree1"),
    TREE2("tree2"),
    TREE3("tree2"),

    SIGN1("sign1"),

    FIRE("fire"),

    CHURCH1("church1"),
    CHURCH2("church2"),
    CHURCH3("church3"),
    CHURCH4("church4"),
    CHURCH5("church5"),
    CHURCH6("church6"),
    CHURCH7("church7"),
    CHURCH8("church8"),
    CHURCH9("church9"),

    CROSS1("cross1"),
    CROSS2("cross2"),

    EXIT("exit"),

    GRAVE1("grave1"),
    GRAVE2("grave2"),

    ALETTER("ALetter"),
    BLETTER("BLetter"),
    CLETTER("CLetter"),
    DLETTER("DLetter"),
    ELETTER("ELetter"),
    FLETTER("FLetter"),
    GLETTER("GLetter"),
    HLETTER("HLetter"),
    ILETTER("ILetter"),
    JLETTER("JLetter"),
    KLETTER("KLetter"),
    LLETTER("LLetter"),
    MLETTER("MLetter"),
    NLETTER("NLetter"),
    OLETTER("OLetter"),
    PLETTER("PLetter"),
    QLETTER("QLetter"),
    RLETTER("RLetter"),
    SLETTER("SLetter"),
    TLETTER("ILetter"),
    ULETTER("ULetter"),
    VLETTER("VLetter"),
    WLETTER("WLetter"),
    XLETTER("XLetter"),
    YLETTER("YLetter"),
    ZLETTER("ZLetter");

    private final String tileName;
//    private final boolean isObstacle;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
