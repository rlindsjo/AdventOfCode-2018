package net.tilialacus.adventofcode2018.day12;

import net.tilialacus.adventofcode2018.Input;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12Part1Test {

    @Test
    void parse() {
        Pots pots = new Pots();
        pots.configure(Input.testinputFor(pots));

        assertEquals("#..#.#..##......###...###", pots.getState());
    }

    @Test
    void step() {
        Pots pots = new Pots();
        pots.configure(Input.testinputFor(pots));

        pots.parseState("##..##...##....#..#..#..##");
        pots.generate();

        assertEquals("#.#...#..#.#....#..#..#...#", pots.getState());
    }

    @Test
    void steps() {
        Pots pots = new Pots();
        pots.configure(Input.testinputFor(pots));

        for (int i = 0; i < 20; i++) {
            pots.generate();
        }
        assertEquals("#....##....#####...#######....#.#..##", pots.getState());
        assertEquals(325, pots.sum());
    }

    @Test
    void solution() {
        Pots pots = new Pots();
        pots.configure(Input.inputFor(pots));

        for (int i = 0; i < 20; i++) {
            pots.generate();
        }
        assertEquals("####.#....#.#.#..##.#......###.#....###...###....#.....#.#....###....#####..#.#.#.......###...###...###...###", pots.getState());
        assertEquals(2995, pots.sum());
    }
}
