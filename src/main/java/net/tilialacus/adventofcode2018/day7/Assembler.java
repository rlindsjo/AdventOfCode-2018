package net.tilialacus.adventofcode2018.day7;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Assembler {

    public static final Comparator DEPENDENCY_ORDER = new Comparator<Step>() {
        @Override
        public int compare(Step step1, Step step2) {
            if (step2.getDepends().contains(step1)) {
                return -1;
            } else if (step1.getDepends().contains(step2)) {
                return 1;
            } else {
                return step1.getName().compareTo(step2.getName());
            }

        }
    };
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

    public Collection<Step> getSteps() {
        return steps.values();
    }

    void buildFullDependency() {
        for (boolean added = true; added;) {
            added = false;
            for (Step step : getSteps()) {
                Set<Step> depends = step.getDepends();
                for (Step depend : depends) {
                    if (depends.addAll(depend.getDepends())) {
                        added = true;
                        break;
                    }
                }
            }
        }
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

        @Override
        public String toString() {
            return name + "(" + depends.stream().map(Step::getName).collect(Collectors.joining(",")) + ")";
        }
    }
}
