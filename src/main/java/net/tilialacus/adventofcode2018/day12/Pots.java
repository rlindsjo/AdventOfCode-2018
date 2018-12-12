package net.tilialacus.adventofcode2018.day12;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Pots {
    private String state;
    private Set<String> generators = new HashSet<>();

    public void configure(List<String> input) {
        Iterator<String> iterator = input.iterator();
        // initial state: #..#.#..##......###...###
        state = iterator.next().substring(15);
        // Skip empty line
        iterator.next();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.endsWith("#")) {
                generators.add(line.substring(0,5));
            }
        }
    }

    public String getState() {
        return state;
    }

    public void generate() {
        StringBuilder sb = new StringBuilder();
        for (int i = -4; i < state.length() + 4; i++) {
            sb.append(generators.contains(getM(i)) ? '#' : '.');
        }

        // Ugly but easy way to get rid of trailing / leading "."
        state = sb.toString().replaceAll("^\\.*|\\.*$", "");
    }

    private String getM(int c) {
        StringBuilder sb = new StringBuilder();
        for (int i = c; i < c+5; i++) {
            if (i < 0 || i >= state.length()) {
                sb.append('.');
            } else {
                sb.append(state.charAt(i));
            }
        }
        return sb.toString();
    }
}
