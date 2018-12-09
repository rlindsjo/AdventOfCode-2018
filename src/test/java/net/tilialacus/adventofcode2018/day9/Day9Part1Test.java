package net.tilialacus.adventofcode2018.day9;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day9Part1Test {

    @Test
    void createGame() {
        Game game = new Game(5);

        assertEquals("(0)", game.toString());
    }

    @Test
    void place5Balls() {
        Game game = new Game(5);
        for (int i = 0; i < 5; i++) {
            game.placeBall();
        }

        assertEquals("0 4 2 (5) 1 3", game.toString());
    }

    @Test
    void place25Balls() {
        Game game = new Game(9);
        for (int i = 0; i < 25; i++) {
            game.placeBall();
        }

        assertEquals("0 16 8 17 4 18 19 2 24 20 (25) 10 21 5 22 11 1 12 6 13 3 14 7 15", game.toString());
        assertArrayEquals(new long [] {0, 0, 0, 0, 32, 0, 0, 0, 0}, game.getScore());
    }

    @Test
    void place6111Balls() {
        Game game = new Game(21);
        for (int i = 0; i < 6111; i++) {
            game.placeBall();
        }

        assertEquals(54718, Arrays.stream(game.getScore()).max().getAsLong());
    }

    @Test
    void result() {
        // 473 players; last marble is worth 70904 points
        Game game = new Game(473);
        for (int i = 0; i < 70904; i++) {
            game.placeBall();
        }

        assertEquals(371284, Arrays.stream(game.getScore()).max().getAsLong());
    }
}
