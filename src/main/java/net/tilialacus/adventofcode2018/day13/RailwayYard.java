package net.tilialacus.adventofcode2018.day13;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static net.tilialacus.adventofcode2018.day13.RailwayYard.Direction.*;

public class RailwayYard {
    public List<Train> getTrains() {
        return trains;
    }

    public void tick(Train train) {
        switch (track[train.row][train.col]) {
            case '/':
                switch (train.dir) {
                    case LEFT:
                        train.dir = DOWN;
                        break;
                    case RIGHT:
                        train.dir = UP;
                        break;
                    case DOWN:
                        train.dir = LEFT;
                        break;
                    case UP:
                        train.dir = RIGHT;
                        break;
                }
                break;
            case '\\':
                switch (train.dir) {
                    case LEFT:
                        train.dir = UP;
                        break;
                    case RIGHT:
                        train.dir = DOWN;
                        break;
                    case DOWN:
                        train.dir = RIGHT;
                        break;
                    case UP:
                        train.dir = LEFT;
                        break;
                }
                break;
            case '+':
                train.intersection();
        }
        train.tick();
    }

    public boolean isCrach() {
        Set<String> positions = new HashSet<>();
        for (Train train : trains) {
            if (!positions.add(train.col + "," + train.row)) {
                return true;
            }
        }
        return false;
    }

    enum Direction {
        LEFT, RIGHT, UP, DOWN;

        public Direction left() {
            switch (this) {
                case LEFT:
                    return DOWN;
                case RIGHT:
                    return UP;
                case DOWN:
                    return RIGHT;
                case UP:
                    return LEFT;
                default:
                    throw new IllegalStateException();
            }
        }

        public Direction right() {
            return left().left().left();
        }
    };


    private final char[][] track;
    private final List<Train> trains;

    public RailwayYard(char[][] track, List<Train> trains) {
        this.track = track;
        this.trains = trains;
    }

    public static RailwayYard parse(List<String> lines) {
        char[][] track = new char[lines.size()][lines.get(0).length()];
        List<Train> trains = new ArrayList<Train>();
        for (int row = 0; row < lines.size(); row++) {
            char[] line = lines.get(row).toCharArray();
            for (int col = 0; col < line.length; col++) {
                switch (line[col]) {
                    case '<':
                        trains.add(new Train(row, col, LEFT));
                        track[row][col] = '-';
                        break;
                    case '>':
                        trains.add(new Train(row, col, RIGHT));
                        track[row][col] = '-';
                        break;
                    case '^':
                        trains.add(new Train(row, col, UP));
                        track[row][col] = '|';
                        break;
                    case 'v':
                        trains.add(new Train(row, col, DOWN));
                        track[row][col] = '|';
                        break;
                    default:
                        track[row][col] = line[col];
                }
            }
        }
        return new RailwayYard(track, trains);
    }

    @Override
    public String toString() {
        return trains.toString();
    }

    static class Train {

        private int row;
        private int col;
        private Direction dir;
        private int intcount = 0;

        public Train(int row, int col, Direction dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }

        @Override
        public String toString() {
            return "{" + col + "," + row + "," + dir + "}";

        }

        public void tick() {
            switch (dir) {
                case LEFT:
                    --col;
                    break;
                case RIGHT:
                    ++col;
                    break;
                case UP:
                    --row;
                    break;
                case DOWN:
                    ++row;
                    break;
            }
        }

        public void intersection() {
            switch (intcount % 3) {
                case 0: dir = dir.left(); break;
                case 2: dir = dir.right(); break;
            }
            ++intcount;
        }
    }
}
