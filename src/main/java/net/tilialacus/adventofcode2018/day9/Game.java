package net.tilialacus.adventofcode2018.day9;

public class Game {

    private final long[] score;
    private Ball zero;
    private Ball current;

    private int currentPlayer;
    private int ballnumber = 0;

    public Game(int players) {
        score = new long[players];
        zero = new Ball(ballnumber++);
        zero.clockwise = zero;
        zero.counterclockwise = zero;
        current = zero;
    }

    public void placeBall() {
        if (ballnumber % 23 == 0) {
            score[currentPlayer] += ballnumber;
            for (int i = 0; i < 7; i++) {
                current = current.counterclockwise;
            }
            score[currentPlayer] += current.score;
            current = current.remove();
            // and remove ball
        } else {
            Ball next = new Ball(ballnumber);
            current = current.clockwise.insert(next);
        }
        ballnumber++;
        currentPlayer = (currentPlayer + 1) % score.length;
    }

    public long[] getScore() {
        return score;
    }

    private static class Ball {
        private final int score;
        private Ball clockwise;
        public Ball counterclockwise;

        public Ball(int score) {
            this.score = score;
        }

        public Ball insert(Ball next) {
            clockwise.counterclockwise = next;
            next.clockwise = clockwise;
            clockwise = next;
            next.counterclockwise = this;
            return next;
        }

        public Ball remove() {
            clockwise.counterclockwise = counterclockwise;
            counterclockwise.clockwise = clockwise;
            return clockwise;
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
