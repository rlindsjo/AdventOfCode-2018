package net.tilialacus.adventofcode2018.day3;

import net.tilialacus.adventofcode2018.Input;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    private static Pattern pattern = Pattern.compile("#\\d+ \\@ (?<x>\\d+),(?<y>\\d+): (?<width>\\d+)x(?<height>\\d+)");

    public static void main(String[] args) throws IOException, URISyntaxException {
        int fabric[][] = new int[1000][1000];
        int mask[][] = new int[1000][1000];
        List<String> claims = Input.inputFor(Part2.class);

        for (String claim : claims) {
            if (mark(claim, fabric) > 0) {
                mark(claim, mask);
            }
        }

        for (String claim : claims) {
            if (mark(claim, mask) == 0) {
                System.out.printf("No overlap for %s%n", claim);
            }
        }
    }

    private static int mark(String claim, int[][] fabric) {
        Matcher matcher = pattern.matcher(claim);
        matcher.matches();

        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        int width = Integer.parseInt(matcher.group("width"));
        int height = Integer.parseInt(matcher.group("height"));

        int overlaps = 0;
        for (int i = x; i < x + width; i ++) {
            for (int j = y; j < y + height; j++) {
                if (fabric[i][j]++ > 0)
                overlaps++;
            }
        }
        return overlaps;
    }
}
