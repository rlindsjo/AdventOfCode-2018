package net.tilialacus.adventofcode2018.day8;

import net.tilialacus.adventofcode2018.Input;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day8Part1Test {

    private static final String sampleInput = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";

    @Test
    void parseInput() {
        String[] data = sampleInput.split(" ");

        assertEquals(16, data.length);

        Node node = Node.parse(data);

        assertEquals(2, node.getChildren().length);
        assertEquals(3, node.getMeta().length);
        assertArrayEquals(new int[] {1, 1, 2}, node.getMeta());

        assertEquals(138, node.getMetaSum());
    }

    @Test
    void sampleMetaSum() {
        String[] data = sampleInput.split(" ");

        Node node = Node.parse(data);

        assertEquals(138, node.getMetaSum());
    }

    @Test
    void result() {
        String[] data = Input.inputFor(Node.class).get(0).split(" ");

        Node node = Node.parse(data);

        assertEquals(46962, node.getMetaSum());
    }
}
