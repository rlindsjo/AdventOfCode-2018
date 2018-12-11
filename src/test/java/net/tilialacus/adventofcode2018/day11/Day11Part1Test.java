package net.tilialacus.adventofcode2018.day11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11Part1Test {

    @Test
    void powerCellValue() {
        PowerGrid powerGrid = new PowerGrid(8);

        int power = powerGrid.calculatePower(3, 5);

        assertEquals(4, power);
    }

    @Test
    void output() {
        PowerGrid powerGrid = new PowerGrid(18);

        assertEquals(
                "-2 -4 4 4 4 \n" +
                "-4 4 4 4 -5 \n" +
                "4 3 3 4 -4 \n" +
                "1 1 2 4 -3 \n" +
                "-1 0 2 -5 -2 \n",
                powerGrid.toString(32, 44 , 5, 5)
        );

        assertEquals(29, powerGrid.sum(33, 45, 3, 3));

        PowerGrid.Coordinate max = powerGrid.findMax(3, 3);

        assertAll(
                () -> assertEquals(33, max.getX()),
                () -> assertEquals(45, max.getY())
        );
    }

    @Test
    void result() {
        PowerGrid powerGrid = new PowerGrid(2866);

        PowerGrid.Coordinate max = powerGrid.findMax(3, 3);

        assertAll(
                () -> assertEquals(20, max.getX()),
                () -> assertEquals(50, max.getY())
        );
    }
}
