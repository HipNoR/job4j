
package ru.job4j.tictactoe;

import java.util.function.BiPredicate;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    private boolean checkLine(int size, BiPredicate<Integer, Integer> predict) {
        int count = 0;
        boolean winner = false;
        for (int out = 0; out < size; out++) {
            if (count == size) {
                winner = true;
                break;
            } else {
                for (int in = 0; in < size; in++) {
                    if (predict.test(out, in)) {
                        count++;
                    } else {
                        count = 0;
                        break;
                    }
                }
            }
        }
        return winner;
    }

    private boolean checkDiagonal(int size, BiPredicate<Integer, Integer> predict) {
        int count = 0;
        boolean winner = false;
        for (int index = 0; index < size; index++) {
            if (predict.test(index, index)) {
                count++;
            } else {
                count = 0;
                break;
            }
        }
        if (count == size) {
            winner = true;
        }
        return winner;
    }

    public boolean isWinnerX() {
        boolean winnerX = false;
        boolean row = this.checkLine(
                table.length,
                (out, in) -> table[out] [in].hasMarkX()
        );
        boolean column = this.checkLine(
                table.length,
                (out, in) -> table[in] [out].hasMarkX()
        );
        boolean down = this.checkDiagonal(
                table.length,
                (in, out) -> table[in] [out].hasMarkX()
        );
        boolean up = this.checkDiagonal(
                table.length,
                (in, out) -> table[table.length - in - 1] [out].hasMarkX()
        );
        if (row || column || down || up) {
            winnerX = true;
        }
        return winnerX;
    }

    public boolean isWinnerO() {
        boolean winnerO = false;
        boolean row = this.checkLine(
                table.length,
                (out, in) -> table[out] [in].hasMarkO()
        );
        boolean column = this.checkLine(
                table.length,
                (out, in) -> table[in] [out].hasMarkO()
        );
        boolean down = this.checkDiagonal(
                table.length,
                (in, out) -> table[in] [out].hasMarkO()
        );
        boolean up = this.checkDiagonal(
                table.length,
                (in, out) -> table[table.length - in - 1] [out].hasMarkO()
        );
        if (row || column || down || up) {
            winnerO = true;
        }
        return winnerO;
    }

    public boolean hasGap() {
        int count = table.length * table.length;
        boolean gap = true;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (table[i] [j].hasMarkX() || table[i] [j].hasMarkO()) {
                    count--;
                }
            }
        }
        if (count == 0) {
            gap = false;
        }
        return gap;
    }
}