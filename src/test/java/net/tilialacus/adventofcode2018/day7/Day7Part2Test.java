package net.tilialacus.adventofcode2018.day7;

import net.tilialacus.adventofcode2018.Input;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day7Part2Test {

    private static List<String> sampleInput = Arrays.asList(
            "Step C must be finished before step A can begin.",
            "Step C must be finished before step F can begin.",
            "Step A must be finished before step B can begin.",
            "Step A must be finished before step D can begin.",
            "Step B must be finished before step E can begin.",
            "Step D must be finished before step E can begin.",
            "Step F must be finished before step E can begin."
    );

    private Assembler assembler = new Assembler();

    @Test
    void sampleInputOrder() {
        for (String sample : sampleInput) {
            assembler.parse(sample);
        }

        Workers workers = new Workers(2, 0);
        String order = workers
                .process(assembler)
                .stream()
                .map(Assembler.Step::getName)
                .collect(Collectors.joining());

        assertEquals(15, workers.getTime());
        assertEquals("CABFDE", order);
    }

    @Test
    void result() {
        for (String sample : Input.inputFor(assembler)) {
            assembler.parse(sample);
        }

        Workers workers = new Workers(5, 60);
        String order = workers
                .process(assembler)
                .stream()
                .map(Assembler.Step::getName)
                .collect(Collectors.joining());

        assertEquals("BQRGMKJSZDCNWYETHULPAFIVXO", order);
        assertEquals(941, workers.getTime());
    }
}
