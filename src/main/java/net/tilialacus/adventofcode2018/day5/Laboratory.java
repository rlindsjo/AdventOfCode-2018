package net.tilialacus.adventofcode2018.day5;

public class Laboratory {

    private static char[] reacts = new char['z' + 1];

    static {
        for (char i = 'A'; i <= 'Z'; i++) {
            reacts[i] = (char)(i + 'a' - 'A');
            reacts[i + 'a' - 'A'] = i;
        }
    }

    static String react(String line) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char current = line.charAt(i);
            if (i == line.length()-1 || reacts[current] != line.charAt(i+1)) {
                b.append(current);
            } else {
                ++i; // Skip next
            }
        }
        return b.toString();
    }

    public static String clean(char r, String line) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char current = line.charAt(i);
            if (r != current && reacts[r] != current) {
                b.append(current);
            }
        }
        return b.toString();
    }
}
