package net.tilialacus.adventofcode2018.day12;

import net.tilialacus.adventofcode2018.Input;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12Part2Test {

    private static final String STEADY = "###.#....###...###....###......###....###....###....###....###.......###....###......###...###........###......###...........###......###....###...###...###.......###...###...###...###";

    @Test
    void solution2() {
        Pots pots = new Pots();
        pots.configure(Input.inputFor(pots));

        int iterations = 0;
        while (!pots.getState().equals(STEADY)) {
            pots.generate();
            iterations++;
        }

        assertEquals(161, iterations);
        assertEquals(73 * iterations + 377, pots.sum());

        // Once in this stable state for some reason the answer is
        // 73 * iterations + 377 (need to think about this, so the pattern "shouts to the right")
        // So the solution is 36500000000377

        assertEquals(3650000000377L, 73 * 50_000_000_000L + 377);
    }
}
