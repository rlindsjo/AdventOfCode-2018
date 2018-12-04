package net.tilialacus.adventofcode2018.day2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Part1 {

    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Path.of(Part1.class.getResource("input.txt").toURI()));

        long twos = lines.stream().filter(i -> hasCount(i, 2)).count();
        long threes = lines.stream().filter(i -> hasCount(i, 3)).count();

        System.out.printf("Twos %d, threes %d gives %d%n", twos, threes, twos * threes);
    }

    private static boolean hasCount(String s, int count) {
        int[] found = new int['z' - 'a' + 1];
        for (char c : s.toCharArray()) {
            found[c - 'a']++;
        }

        for (int i : found) {
            if (i == count) {
                return true;
            }
        }
        return false;
    }
}
