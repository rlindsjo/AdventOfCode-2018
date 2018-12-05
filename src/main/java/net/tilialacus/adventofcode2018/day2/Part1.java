package net.tilialacus.adventofcode2018.day2;

import java.util.List;

import static net.tilialacus.adventofcode2018.Input.inputFor;

public class Part1 {

    public static void main(String[] args)  {
        List<String> lines = inputFor(Part1.class);

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
