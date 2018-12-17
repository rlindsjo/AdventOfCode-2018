package net.tilialacus.adventofcode2018.day13;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day13Part1 {

    @Test
    void parse() {
        List<String> lines = Arrays.asList(
                "/->-\\        ",
                "|   |  /----\\",
                "| /-+--+-\\  |",
                "| | |  | v  |",
                "\\-+-/  \\-+--/",
                "  \\------/   ");

        RailwayYard yard = RailwayYard.parse(lines);

        assertEquals("[{2,0,RIGHT}, {9,3,DOWN}]", yard.toString());
    }

    @Test
    void tick() {
        List<String> lines = Arrays.asList(
                "/->-\\        ",
                "|   |  /----\\",
                "| /-+--+-\\  |",
                "| | |  | v  |",
                "\\-+-/  \\-+--/",
                "  \\------/   ");

        RailwayYard yard = RailwayYard.parse(lines);

        Iterator<RailwayYard.Train> iter = new Infinate(yard.getTrains());
        while (!yard.isCrach()) {
            yard.tick(iter.next());
        }

        assertEquals("[{7,3,UP}, {7,3,DOWN}]", yard.toString());
    }

    private class Infinate implements Iterator<RailwayYard.Train> {
        private final ArrayList list;
        private Iterator<RailwayYard.Train> iter = Collections.emptyIterator();

        public Infinate(List<RailwayYard.Train> trains) {
            this.list = new ArrayList(trains);
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public RailwayYard.Train next() {
            if (!iter.hasNext()) {
                iter = list.iterator();
            }
            return iter.next();
        }
    }
}
