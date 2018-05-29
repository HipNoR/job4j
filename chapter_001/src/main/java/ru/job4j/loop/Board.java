package ru.job4j.loop;

/**
 * Class Board - chessboard.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class Board {

    /**
     * Method draws a chessboard.
     * @param width - width of the board.
     * @param height - height of the board.
     * @return - returns the chessboard to the terminal.
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int out = 0; out < height; out++) {
            for (int in = 0; in < width; in++) {
                if ((in + out) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }
}
