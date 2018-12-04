package net.tilialacus.adventofcode2018.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Part1 {

    public static void main(String[] args) throws URISyntaxException, IOException {
        BufferedReader reader = Files.newBufferedReader(Path.of(Part1.class.getResource("input.txt").toURI()));

        int frequency = reader.lines().mapToInt(Integer::valueOf).sum();

        System.out.printf("Frequency %d%n", frequency);
    }
}
