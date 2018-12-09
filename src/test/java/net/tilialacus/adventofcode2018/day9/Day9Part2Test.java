package net.tilialacus.adventofcode2018.day9;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day9Part2Test {

    @Test
    void result() {
        // 473 players; last marble is worth 7090400 points
        Game game = new Game(473);
        for (int i = 0; i < 7090400; i++) {
            game.placeBall();
        }

        assertEquals(3038972494L, Arrays.stream(game.getScore()).max().getAsLong());
    }
}
