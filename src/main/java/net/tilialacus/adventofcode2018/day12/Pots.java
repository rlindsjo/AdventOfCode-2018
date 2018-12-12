package net.tilialacus.adventofcode2018.day12;

import java.util.*;
import java.util.stream.Collectors;

public class Pots {
    private List<Pot> state = new LinkedList<>();
    private Set<String> generators = new HashSet<>();

    public void configure(List<String> input) {
        Iterator<String> iterator = input.iterator();
        // initial state: #..#.#..##......###...###
        parseState(iterator.next().substring(15));

        // Skip empty line
        iterator.next();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.endsWith("#")) {
                generators.add(line.substring(0,5));
            }
        }
    }

    void parseState(String input) {
        state.clear();
        for (int i = 0; i < input.length(); i++) {
            state.add(new Pot(i, input.charAt(i) == '#'));
        }
    }

    public String getState() {
        return state.stream().map(pot -> pot.plant ? "#" : ".").collect(Collectors.joining());
    }

    public void generate() {
        // Pad right and left to simplify calculations
        for (int i = 0; i < 3; i++) {
            state.add(0, new Pot(state.get(0).i - 1, false));
            state.add(new Pot(state.get(state.size() - 1).i + 1, false));
        }

        List<Pot> next = new LinkedList<>();
        for (int i = 0; i < state.size(); i++) {
            next.add(new Pot(state.get(i).i, generators.contains(getPattern(i))));
        }

        while (!next.get(0).plant) {
            next.remove(0);
        }
        while (!next.get(next.size() - 1).plant) {
            next.remove(next.size() - 1);
        }
        state = next;
    }

    private String getPattern(int c) {
        StringBuilder sb = new StringBuilder();
        for (int i = c - 2; i <= c+2; i++) {
            if (i < 0 || i >= state.size()) {
                sb.append('.');
            } else {
                sb.append(state.get(i).plant ? '#' : '.');
            }
        }
        return sb.toString();
    }

    public int sum() {
        return state.stream().filter(Pot::isPlant).mapToInt(Pot::getI).sum();
    }

    static class Pot {
        private final int i;
        private boolean plant;

        public Pot(int i, boolean plant) {
            this.i = i;
            this.plant = plant;
        }

        public int getI() {
            return i;
        }

        public boolean isPlant() {
            return plant;
        }
    }
}
