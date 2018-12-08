package net.tilialacus.adventofcode2018;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Input {

    public static List<String> inputFor(Class clazz) {
        try {
            return Files.readAllLines(Path.of(clazz.getResource("input.txt").toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Could not load demo data for " + clazz, e);
        }
    }

    public static List<String> inputFor(Object problem) {
        return inputFor(problem.getClass());
    }
}
