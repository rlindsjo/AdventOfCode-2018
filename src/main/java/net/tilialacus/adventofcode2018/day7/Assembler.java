package net.tilialacus.adventofcode2018.day7;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assembler {

    // "Step C must be finished before step A can begin."
    Pattern parser = Pattern.compile("Step (?<step>.) must be finished before step (?<depend>.) can begin\\.");

    public Step parse(String input) {
        Matcher matcher = parser.matcher(input);
        matcher.matches();
        return new Step(matcher.group("step"), matcher.group("depend"));
    }

    class Step {
        private final String name;
        private final String depends;

        public Step(String name, String depends) {
            this.name = name;
            this.depends = depends;
        }

        public String getName() {
            return name;
        }

    }
}
