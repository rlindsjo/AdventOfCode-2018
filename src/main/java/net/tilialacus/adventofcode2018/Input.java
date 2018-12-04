package net.tilialacus.adventofcode2018;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Input {

    public static List<String> inputFor(Class clazz) throws IOException, URISyntaxException {
        return Files.readAllLines(Path.of(clazz.getResource("input.txt").toURI()));
    }

}
