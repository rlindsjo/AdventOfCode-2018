package net.tilialacus.adventofcode2018.day3;

import net.tilialacus.adventofcode2018.Input;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    private static Pattern pattern = Pattern.compile("#\\d+ \\@ (?<x>\\d+),(?<y>\\d+): (?<width>\\d+)x(?<height>\\d+)");

    public static void main(String[] args) {
        int fabric[][] = new int[1000][1000];
        List<String> claims = Input.inputFor(Part1.class);

        for (String claim : claims) {
            mark(claim, fabric);
        }

        System.out.printf("Overlaps %d%n", countOverlaps(fabric));
    }

    private static int countOverlaps(int[][] fabric) {
        int overlaps = 0;
        for (int[] cols : fabric) {
            for (int claims : cols) {
                if (claims > 1) {
                    overlaps++;
                }
            }
        }
        return overlaps;
    }

    private static void mark(String claim, int[][] fabric) {
        Matcher matcher = pattern.matcher(claim);
        matcher.matches();

        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        int width = Integer.parseInt(matcher.group("width"));
        int height = Integer.parseInt(matcher.group("height"));

        for (int i = x; i < x + width; i ++) {
            for (int j = y; j < y + height; j++) {
                fabric[i][j]++;
            }
        }
    }
}
