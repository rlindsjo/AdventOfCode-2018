package net.tilialacus.adventofcode2018.day1;

import org.junit.jupiter.api.Test;

import static net.tilialacus.adventofcode2018.Input.inputFor;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Part2Test {

    private final Day1 day1 = new Day1();

    @Test
    public void result() {
        int frequency = day1.firstDuplicate(inputFor(day1).stream().mapToInt(Integer::parseInt));
        assertEquals(83445, frequency);
    }
}