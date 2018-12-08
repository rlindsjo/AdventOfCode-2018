package net.tilialacus.adventofcode2018.day6;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static net.tilialacus.adventofcode2018.Input.inputFor;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Part1Test {

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
        for (String part : sampleInput) {
            Grid.Coordinate coordinate = grid.parse(part);
            grid.map(coordinate);
        }

        assertArrayEquals(new int[] {-1, -1, -1, 9, 17, -1}, grid.areas());
    }

    @Test
    public void result() {
        for (String part : inputFor(grid)) {
            Grid.Coordinate coordinate = grid.parse(part);
            grid.map(coordinate);
        }

        assertEquals(5035, Arrays.stream(grid.areas()).max().orElse(-1));
    }
}
