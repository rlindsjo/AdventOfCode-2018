package net.tilialacus.adventofcode2018.day10;

import net.tilialacus.adventofcode2018.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static net.tilialacus.adventofcode2018.day10.Galaxy.Particle;
import static net.tilialacus.adventofcode2018.day10.Galaxy.particle;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10Part1Test {

    private final Galaxy galaxy = new Galaxy();

    @Test
    public void setupGalaxy() {
        galaxy.add(particle(-1, -1, 0, 0));
        galaxy.add(particle(1, 1, -1, 1));

        assertAll(
                () -> assertEquals(-1, galaxy.getLeft()),
                () -> assertEquals(-1, galaxy.getTop()),
                () -> assertEquals(1, galaxy.getRight()),
                () -> assertEquals(1, galaxy.getBottom())
        );

        galaxy.tick();

        assertAll(
                () -> assertEquals(-1, galaxy.getLeft()),
                () -> assertEquals(-1, galaxy.getTop()),
                () -> assertEquals(0, galaxy.getRight()),
                () -> assertEquals(2, galaxy.getBottom())
        );
    }

    @Test
    void parse() {
        Particle particle = particle("position=<-19942, -39989> velocity=< 2,  4>");
        assertAll(
                () -> assertEquals(-19942, particle.getX()),
                () -> assertEquals(-39989, particle.getY())
        );
    }

    @Test
    void resultFindMinimumSteps() {
        List<String> lines = Input.inputFor(galaxy);
        for (String line : lines) {
            galaxy.add(Galaxy.particle(line));
        }

        int pw = Integer.MAX_VALUE;
        int width = galaxy.getRight() - galaxy.getLeft();
        int i = 0;
        while (pw > width) {
           ++i;
           galaxy.tick();
           pw = width;
           width = galaxy.getRight() - galaxy.getLeft();
        }
        System.err.println(i - 1);
    }

    @Test
    void resultText() {
        List<String> lines = Input.inputFor(galaxy);
        for (String line : lines) {
            galaxy.add(Galaxy.particle(line));
        }

        for (int i = 0; i < 10036; i++) {
            galaxy.tick();
        }
        assertEquals(
                "   ###     ###  #    #  ######  #    #  #    #  ######  ##### \n" +
                        "    #       #   #    #       #  #    #  #   #   #       #    #\n" +
                        "    #       #    #  #        #  #    #  #  #    #       #    #\n" +
                        "    #       #    #  #       #   #    #  # #     #       #    #\n" +
                        "    #       #     ##       #    ######  ##      #####   ##### \n" +
                        "    #       #     ##      #     #    #  ##      #       #     \n" +
                        "    #       #    #  #    #      #    #  # #     #       #     \n" +
                        "#   #   #   #    #  #   #       #    #  #  #    #       #     \n" +
                        "#   #   #   #   #    #  #       #    #  #   #   #       #     \n" +
                        " ###     ###    #    #  ######  #    #  #    #  #       #     \n",
                galaxy.toString()
        );
    }
}
