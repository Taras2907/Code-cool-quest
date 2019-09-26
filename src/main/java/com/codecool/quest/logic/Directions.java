package com.codecool.quest.logic;

import java.util.List;
import java.util.Random;

public enum Directions {

        UP(0,-1),
        DOWN(0,1),
        LEFT(-1, 0),
        RIGHT(1, 0);
        
        private final int dx;
        private final int dy;

        Directions(int dx, int dy) {
                this.dx = dx;
                this.dy = dy;
        }

        private static final List<Directions> VALUES = List.of(values());
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static Directions randomDirection()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }

        public int getDx() {
                return this.dx;
        }

        public int getDy() {
                return this.dy;
        }
}
