package net.tilialacus.adventofcode2018.day4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Guards {

    private static final Pattern guardParser = Pattern.compile("\\[\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}\\] Guard #(?<guardid>\\d+) begins shift");
    private static final Pattern awakeParser = Pattern.compile("\\[\\d{4}-\\d{2}-\\d{2} \\d{2}:(?<minute>\\d{2})\\] wakes up");
    private static final Pattern sleepParser = Pattern.compile("\\[\\d{4}-\\d{2}-\\d{2} \\d{2}:(?<minute>\\d{2})\\] falls asleep");

    public Map<String,Guard> guards = new HashMap<>();
    private Guard guard;

    public void process(String line) {
        Matcher guardMatcher = guardParser.matcher(line);
        Matcher sleepMatcher = sleepParser.matcher(line);
        Matcher awakeMatcher = awakeParser.matcher(line);
        if (guardMatcher.matches()) {
            guard = guards.computeIfAbsent(guardMatcher.group("guardid"), Guard::new);
        } else if (sleepMatcher.matches()) {
            guard.sleep(Integer.parseInt(sleepMatcher.group("minute")));
        } else if (awakeMatcher.matches()) {
            guard.wake(Integer.parseInt(awakeMatcher.group("minute")));
        }
    }

    public void process(List<String> lines) {
        for (String line : lines) {
            process(line);
        }
    }

    static class Guard {
        int[] times = new int[60];
        String name;
        int asleep = -1;

        public Guard(String name) {
            this.name = name;
        }

        public void sleep(int minute) {
            this.asleep = minute;
        }

        public void wake(int minute) {
            for (int i = asleep; i < minute; i++) {
                times[i]++;
            }
            asleep = -1;
        }

        public int sleepiestMinute() {
            int sleepiest = 0;
            for (int i = 0; i < times.length; i++) {
                if (times[sleepiest] < times[i]) {
                    sleepiest = i;
                }
            }
            return sleepiest;
        }
    }
}
