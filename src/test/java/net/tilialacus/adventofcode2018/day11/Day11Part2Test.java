package net.tilialacus.adventofcode2018.day11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11Part2Test {

    @Test
    void result() {
        PowerGrid powerGrid = new PowerGrid(2866);

        PowerGrid.Coordinate best = new PowerGrid.Coordinate(0, 0, Integer.MIN_VALUE);
        int bestSize = 0;
        for (int size = 1; size <= 300; size++) {
            PowerGrid.Coordinate max = powerGrid.findMax(size, size);
            if (max.getSum() > best.getSum()) {
                best = max;
                bestSize = size;
            }
        }

        PowerGrid.Coordinate finalBest = best;
        int finalBestSize = bestSize;
        assertAll(
                () -> assertEquals(238, finalBest.getX()),
                () -> assertEquals(278, finalBest.getY()),
                () -> assertEquals(9, finalBestSize)
        );

    }
}
