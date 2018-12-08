package net.tilialacus.adventofcode2018.day7;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    public List<Step> processAll() {
        List<Step> processed = new ArrayList<>();
        Assembler.Step step;
        while(steps.size() > 0) {
            processed.add(process(getNext()));
        }
        return processed;
    }

    public Step getNext() {
        return steps.values().stream().filter(Step::available).sorted(Comparator.comparing(Step::getName)).findFirst().orElse(null);
    }

    public Step process(Step step) {
        steps.remove(step.getName());
        steps.values().forEach(it -> it.getDepends().remove(step));
        return step;
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

        public boolean doesDepend(Step step) {
            return depends.contains(step) || depends.stream().anyMatch(it -> it.doesDepend(step));
        }

        public boolean available() {
            return depends.isEmpty();
        }
    }
}
