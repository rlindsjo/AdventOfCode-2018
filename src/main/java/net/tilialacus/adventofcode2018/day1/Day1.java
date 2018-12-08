package net.tilialacus.adventofcode2018.day1;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class Day1 {

    public int sumFrequencies(IntStream hops) {
        return hops.sum();
    }

    public int firstDuplicate(IntStream hops) {
        int[] cached = hops.toArray();
        int currentFrequency = 0;

        Set<Integer> frequencies = new HashSet<>();
        frequencies.add(currentFrequency);

        int index = 0;
        while (frequencies.add(currentFrequency += cached[index++ % cached.length]));

        return currentFrequency;
    }
}
