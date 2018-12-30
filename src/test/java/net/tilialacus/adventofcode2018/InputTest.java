package net.tilialacus.adventofcode2018;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputTest {

    @Test
    public void loadInput() {
        List<String> lines = Input.inputFor(this);
        assertEquals(Arrays.asList("Line 1", "Line 2"), lines);
    }
}