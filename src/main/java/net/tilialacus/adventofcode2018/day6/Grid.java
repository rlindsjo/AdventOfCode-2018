package net.tilialacus.adventofcode2018.day6;


import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Grid {
    private Coordinate min = new Coordinate(Integer.MAX_VALUE, Integer.MAX_VALUE);
    private Coordinate max  = new Coordinate(Integer.MIN_VALUE, Integer.MIN_VALUE);

    private List<Coordinate> coordinates = new ArrayList<>();

    public Coordinate parse(String part) {
        return Coordinate.of(part.split(", "));
    }

    public void map(Coordinate coordinate) {
        if (coordinate.x < min.x) {
            min = new Coordinate(coordinate.x, min.y);
        }
        if (coordinate.x > max.x) {
            max = new Coordinate(coordinate.x, max.y);
        }
        if (coordinate.y < min.y) {
            min = new Coordinate(min.x, coordinate.y);
        }
        if (coordinate.y > max.y) {
            max = new Coordinate(max.x, coordinate.y);
        }
        coordinates.add(coordinate);
    }

    static class Coordinate {
        private final int x;
        private final int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static Coordinate of(String[] parts) {
            return new Coordinate(parseInt(parts[0]), parseInt(parts[1]));
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    public int[] areas() {
        int[] areas = new int [coordinates.size()];
        for (int y = min.y; y <= max.y; y++) {
            for (int x = min.x; x <= max.x; x++) {
                int best = -1;
                int distance = Integer.MAX_VALUE;
                for (int i = 0; i < coordinates.size(); i++) {
                    Coordinate coordinate = coordinates.get(i);
                    int cd = Math.abs(coordinate.x - x) + Math.abs(coordinate.y - y);
                    if (cd == distance) {
                        best = -1; // Tie
                    } else if (cd < distance) {
                        best = i;
                        distance = cd;
                    }
                }
                if (best != -1) {
                    if (x == min.x || x == max.x || y == min.y || y == max.y) {
                        areas[best] = -1; // infinate
                    } else if (areas[best] != -1){
                        areas[best]++;
                    }
                }
            }
        }
        return areas;
    }
}
