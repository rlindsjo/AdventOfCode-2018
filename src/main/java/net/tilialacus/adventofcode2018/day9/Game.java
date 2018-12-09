package net.tilialacus.adventofcode2018.day9;

public class Game {

    private final int[] score;
    private Ball zero;
    private Ball current;

    public Game(int players) {
        score = new int[players];
        zero = new Ball(0);
        zero.clockwise = zero;
        current = zero;
    }

    private static class Ball {
        private final int score;
        private Ball clockwise;

        public Ball(int score) {
            this.score = score;
        }
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        Ball traverse = zero;
        do {
            display(b, traverse);
        } while ((traverse = traverse.clockwise) != zero);
        return b.toString();
    }

    private void display(StringBuilder b, Ball ball) {
        if (b.length() > 0) {
            b.append(' ');
        }
        if (ball == current) {
            b.append('(').append(ball.score).append(')');
        } else {
            b.append(ball.score);
        }
    }
}
