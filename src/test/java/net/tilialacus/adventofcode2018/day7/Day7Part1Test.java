package net.tilialacus.adventofcode2018.day7;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private Assembler assembler = new Assembler();

    @Test
    void parseLine() {
        Assembler.Step step = assembler.parse("Step C must be finished before step A can begin.");

        assertEquals("A", step.getName());
        assertEquals(Collections.singleton(assembler.getStep("C")), step.getDepends());
    }

    @Test
    void sampleInputDependencies() {
        for (String sample : sampleInput) {
            assembler.parse(sample);
        }
        assembler.buildFullDependency();
        Set<Assembler.Step> expected = Stream.of("A", "B", "C", "D", "F").map(assembler::getStep).collect(Collectors.toSet());
        assertEquals(expected, assembler.getStep("E").getDepends());
    }
}
