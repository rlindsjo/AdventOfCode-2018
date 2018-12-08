package net.tilialacus.adventofcode2018.day1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static net.tilialacus.adventofcode2018.Input.inputFor;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Part1Test {

    private final Day1 day1 = new Day1();

    @Test
    public void sumSomeFrequencies() {
        int frequency = day1.sumFrequencies(Arrays.stream(new int[]{1, -2, 3}));
        assertEquals(2, frequency);
    }

    @Test void exampleOne() {
        int frequency = day1.sumFrequencies(Stream.of("+1", "-2", "+3", "+1").mapToInt(Integer::parseInt));
        assertEquals(3, frequency);
    }

    @Test
    public void result() {
        int frequency = day1.sumFrequencies(inputFor(day1).stream().mapToInt(Integer::parseInt));
        assertEquals(590, frequency);
    }
}