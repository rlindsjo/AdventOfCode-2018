package net.tilialacus.adventofcode2018.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Galaxy {
    // position=<-19942, -39989> velocity=< 2,  4>
    private static final Pattern PARSER = Pattern.compile("position=<\\s*(?<x>-?\\d+),\\s*(?<y>-?\\d+)> velocity=<\\s*(?<vx>-?\\d+),\\s*(?<vy>-?\\d+)>");
    private List<Particle> particles = new ArrayList<>();

    public static Particle particle(int x, int y, int vx, int vy) {
        return new Particle(x, y, vx, vy);
    }

    public static Particle particle(String text) {
        Matcher matcher = PARSER.matcher(text);
        matcher.matches();
        Function<String,Integer> value = term -> Integer.parseInt(matcher.group(term));
        return new Particle(
                value.apply("x"),
                value.apply("y"),
                value.apply("vx"),
                value.apply("vy"));
    }

    public void add(Particle particle) {
        particles.add(particle);
    }

    public int getLeft() {
        return particles.stream().mapToInt(Particle::getX).min().getAsInt();
    }

    public int getTop() {
        return particles.stream().mapToInt(Particle::getY).min().getAsInt();
    }

    public int getRight() {
        return particles.stream().mapToInt(Particle::getX).max().getAsInt();
    }

    public int getBottom() {
        return particles.stream().mapToInt(Particle::getY).max().getAsInt();
    }

    public void tick() {
        particles.forEach(Particle::tick);
    }

    @Override
    public String toString() {
        int left = getLeft();
        int top = getTop();
        boolean[][] data = new boolean[getBottom() - top + 1][getRight()-getLeft() + 1];
        particles.forEach(p -> data[p.y - top][p.x -left] = true);
        StringBuffer sb = new StringBuffer();
        for (boolean[] line : data) {
            for (boolean b : line) {
                sb.append(b ? '#' : ' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    static class Particle {
        private int x;
        private int y;
        private final int vx;
        private final int vy;

        public Particle(int x, int y, int vx, int vy) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
        }

        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }

        public void tick() {
            x += vx;
            y += vy;
        }
    }
}
