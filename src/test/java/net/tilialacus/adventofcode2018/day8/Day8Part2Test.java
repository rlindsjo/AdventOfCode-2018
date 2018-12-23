package net.tilialacus.adventofcode2018.day8;

import net.tilialacus.adventofcode2018.Input;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day8Part2Test {

    private static final String sampleInput = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";

    @Test
    void sampleMetaSum() {
        String[] data = sampleInput.split(" ");

        Node node = Node.parse(data);

        assertEquals(66, node.getIndexedMetaSum());
    }

    @Test
    void result() {
        String[] data = Input.inputFor(this).get(0).split(" ");

        Node node = Node.parse(data);

        assertEquals(22633, node.getIndexedMetaSum());
    }

}
