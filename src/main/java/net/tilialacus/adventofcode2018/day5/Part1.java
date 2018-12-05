package net.tilialacus.adventofcode2018.day5;

import net.tilialacus.adventofcode2018.Input;

public class Part1 {

    public static void main(String[] args) {
        String line = Input.inputFor(Part1.class).get(0);

        int prev;
        do {
            prev = line.length();
            line = Laboratory.react(line);
        } while (prev > line.length());
        System.out.println("Length: " + line.length());
    }
}
