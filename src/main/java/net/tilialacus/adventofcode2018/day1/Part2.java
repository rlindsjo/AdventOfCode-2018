package net.tilialacus.adventofcode2018.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class Part2 {

    public static void main(String[] args) throws URISyntaxException, IOException {
        int[] ints = Files.readAllLines(Path.of(Part2.class.getResource("input.txt").toURI())).stream().mapToInt(Integer::parseInt).toArray();

        int frequency = 0;
        Set<Integer> frequencies = new HashSet<>();
        frequencies.add(frequency);

        int index = 0;
        while (frequencies.add(frequency += ints[index++ % ints.length]));

        System.out.printf("Frequency %d%n", frequency);
    }
}