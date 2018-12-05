package net.tilialacus.adventofcode2018.day4;

import net.tilialacus.adventofcode2018.Input;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Part2 {

    private static final Function<Guards.Guard,Integer> MAX_SLEEP = i -> i.times[i.sleepiestMinute()];

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> lines = Input.inputFor(Part2.class);
        lines.sort(Comparator.naturalOrder());
        Guards guards = new Guards();
        guards.process(lines);

        Optional<Guards.Guard> max = guards.guards.values().stream().max(Comparator.comparing(MAX_SLEEP));

        max.ifPresent(g -> System.out.printf("%s %d %d%n",
                g.name,
                g.sleepiestMinute(),
                Integer.parseInt(g.name) * g.sleepiestMinute()));
    }
}
