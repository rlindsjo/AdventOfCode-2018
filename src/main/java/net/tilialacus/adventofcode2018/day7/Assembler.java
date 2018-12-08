package net.tilialacus.adventofcode2018.day7;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assembler {

    // "Step C must be finished before step A can begin."
    private Pattern parser = Pattern.compile("Step (?<step>.) must be finished before step (?<blocks>.) can begin\\.");

    private Map<String,Step> steps = new HashMap<>();

    public Step parse(String input) {
        Matcher matcher = parser.matcher(input);
        matcher.matches();

        Step step = getStep(matcher.group("blocks"));
        step.dependsOn(getStep(matcher.group("step")));

        return step;
    }

    public Step getStep(String name) {
        return steps.computeIfAbsent(name, Step::new);
    }

    class Step {
        private final String name;
        private final Set<Step> depends = new HashSet<>();

        public Step(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Set<Step> getDepends() {
            return depends;
        }

        public void dependsOn(Step step) {
            depends.add(step);
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return Objects.equals(name, ((Step) o).name);
        }
    }
}
