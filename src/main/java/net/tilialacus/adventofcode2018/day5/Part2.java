package net.tilialacus.adventofcode2018.day5;

import net.tilialacus.adventofcode2018.Input;

public class Part2 {

    public static void main(String[] args) {
        String line = Input.inputFor(Part2.class).get(0);

        int min = Integer.MAX_VALUE;
        for (char r = 'A'; r <= 'Z'; r++) {
            int prev;
            String cleaned = Laboratory.clean(r, line);
            do {
                prev = cleaned.length();
                cleaned = Laboratory.react(cleaned);
            } while (prev > cleaned.length());
            if (min > cleaned.length()) {
                min = cleaned.length();
                System.out.println("Shortest so far: " + r + ": Length: " + cleaned.length());
            }
        }
    }
}
