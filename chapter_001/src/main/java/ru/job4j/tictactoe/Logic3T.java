
package ru.job4j.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
        int count = 0;
        boolean winnerX = false;
        for (int i = 0; i < table.length; i++) {
            if (count == table.length) {
                break;
            } else {
                for (int j = 0; j < table.length; j++) {
                    if (table[i][j].hasMarkX()) {
                        count++;
                    } else {
                        count = 0;
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < table.length; i++) {
            if (count == table.length) {
                break;
            } else {
                for (int j = 0; j < table.length; j++) {
                    if (table[j][i].hasMarkX()) {
                        count++;
                    } else {
                        count = 0;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < table.length; i++) {
            if (count == table.length) {
                break;
            } else if (table[i][i].hasMarkX()) {
                count++;
            } else {
                count = 0;
                break;
            }
        }
        for (int i = 0; i < table.length; i++) {
            if (count == table.length) {
                break;
            } else if (table[table.length - i - 1][i].hasMarkX()) {
                count++;
            } else {
                count = 0;
                break;
            }
        }
        if (count == 3) {
            winnerX = true;
        }
        return winnerX;
    }


    public boolean isWinnerO() {
        int count = 0;
        boolean winnerO = false;
        for (int i = 0; i < table.length; i++) {
            if (count == table.length) {
                break;
            } else {
                for (int j = 0; j < table.length; j++) {
                    if (table[i][j].hasMarkO()) {
                        count++;
                    } else {
                        count = 0;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < table.length; i++) {
            if (count == table.length) {
                break;
            } else {
                for (int j = 0; j < table.length; j++) {
                    if (table[j][i].hasMarkO()) {
                        count++;
                    } else {
                        count = 0;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < table.length; i++) {
            if (count == table.length) {
                break;
            } else if (table[i][i].hasMarkO()) {
                count++;
            } else {
                count = 0;
                break;
            }
        }
        for (int i = 0; i < table.length; i++) {
            if (count == table.length) {
                break;
            } else if (table[table.length - i - 1][i].hasMarkO()) {
                count++;
            } else {
                count = 0;
                break;
            }
        }
        if (count == table.length) {
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