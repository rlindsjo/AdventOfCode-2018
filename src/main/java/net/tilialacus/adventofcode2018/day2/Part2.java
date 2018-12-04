package net.tilialacus.adventofcode2018.day2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static net.tilialacus.adventofcode2018.Input.inputFor;

public class Part2 {

    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> lines = inputFor(Part2.class);

        for (int i = 0; i < lines.size() -1; i ++) {
            for (int j = i+1 ; j < lines.size(); j++) {
                String base = lines.get(i);
                if (countDifferences(base, lines.get(j)) == 1) {
                    System.out.printf("%s%n%s%n", base, lines.get(j));

                }
            }
        }
    }

    private static int countDifferences(String first, String second) {
        int differences = 0;
        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) != second.charAt(i)) {
                differences++;
            }
        }
        return differences;
    }
}
