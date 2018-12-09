package net.tilialacus.adventofcode2018.day9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day9Part1Test {

    @Test
    void createGame() {
        Game game = new Game(5);

        assertEquals("(0)", game.toString());
    }
}
