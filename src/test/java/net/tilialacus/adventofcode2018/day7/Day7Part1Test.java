package net.tilialacus.adventofcode2018.day7;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day7Part1Test {

    private static List<String> sampleInput = Arrays.asList(
            "Step C must be finished before step A can begin.",
            "Step C must be finished before step F can begin.",
            "Step A must be finished before step B can begin.",
            "Step A must be finished before step D can begin.",
            "Step B must be finished before step E can begin.",
            "Step D must be finished before step E can begin.",
            "Step F must be finished before step E can begin."
    );

    private Assembler assempler = new Assembler();

    @Test
    void parseLine() {
        Assembler.Step step = assempler.parse("Step C must be finished before step A can begin.");

        assertEquals("C", step.getName());

    }
}
