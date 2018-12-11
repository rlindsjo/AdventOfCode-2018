package net.tilialacus.adventofcode2018.day11;

public class PowerGrid {
    private final int serial;
    private final int[][] cached;

    public PowerGrid(int serial) {
        this.serial = serial;
        cached = new int[300][300];
        for (int x = 1; x <= 300; x++) {
            for (int y = 1; y <= 300; y++) {
                cached[x-1][y-1] = calculatePower(x, y);
            }
        }
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
                sb.append(cachedPower(j, i)).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    private int cachedPower(int x, int y) {
        return cached[x-1][y-1];
    }

    public Coordinate findMax(int w, int h) {
        Coordinate best = new Coordinate(0, 0, Integer.MIN_VALUE);
        for (int x = 1; x <= 300 - w; x++) {
            for (int y = 1; y <= 300 - h; y++) {
                int sum = sum(x, y, w, h);
                if (sum > best.sum) {
                    best = new Coordinate(x, y, sum);
                }
            }
        }
        return best;
    }

    int sum(int x, int y, int w, int h) {
        int sum = 0;
        for (int dx = x; dx < x + w; dx++) {
            for (int dy = y; dy < y + h; dy++) {
                sum += cachedPower(dx, dy);
            }
        }
        return sum;
    }

    static class Coordinate {
        private final int x;
        private final int y;
        private final int sum;

        public Coordinate(int x, int y, int sum) {
            this.x = x;
            this.y = y;
            this.sum = sum;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getSum() {
            return sum;
        }
    }
}
