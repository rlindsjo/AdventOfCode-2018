package net.tilialacus.adventofcode2018.day11;

public class PowerGrid {
    private final int serial;

    public PowerGrid(int serial) {
        this.serial = serial;
    }

    public int calculatePower(int x, int y) {
        int rackID = x + 10;
        int power = (rackID * y + serial) * rackID;
        return (power/100) % 10 - 5 ;
    }

    public String toString(int x, int y, int w, int h) {
        StringBuilder sb = new StringBuilder();
        for (int i = y; i < y + h; i++) {
            for (int j = x; j < x + w; j++) {
                sb.append(calculatePower(j, i)).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public Coordinate findMax(int w, int h) {
        Coordinate best = new Coordinate(0, 0);
        int max = Integer.MIN_VALUE;
        for (int x = 1; x <= 300 - w; x++) {
            for (int y = 0; y <= 300 - h; y++) {
                int sum = sum(x, y, w, h);
                if (sum > max) {
                    best = new Coordinate(x, y);
                    max = sum;
                }
            }
        }
        return best;
    }

    int sum(int x, int y, int w, int h) {
        int sum = 0;
        for (int dx = x; dx < x + w; dx++) {
            for (int dy = y; dy < y + h; dy++) {
                sum += calculatePower(dx, dy);
            }
        }
        return sum;
    }

    class Coordinate {
        private final int x;
        private final int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
