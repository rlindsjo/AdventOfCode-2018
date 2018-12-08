package net.tilialacus.adventofcode2018.day6;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static net.tilialacus.adventofcode2018.Input.inputFor;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Part2Test {

    private final List<String> sampleInput = Arrays.asList(
            "1, 1",
            "1, 6",
            "8, 3",
            "3, 4",
            "5, 5",
            "8, 9");
    private final Grid grid = new Grid();

    @Test
    public void example() {
        grid.mapAll(sampleInput);

        assertEquals(16, grid.limitedArea(32));
    }

    @Test
    public void result() {
        grid.mapAll(inputFor(grid));

        assertEquals(35294, grid.limitedArea(10000));
    }
}
