package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Logic of moving BomberMan.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 04.09.2018
 */
public class BomberMove {
    private final ReentrantLock[][] board;
    private final int size;
    private int deltaX = 1;
    private int deltaY = 1;
    private boolean hor = true;
    private boolean highY = false;
    private boolean highX = false;

    public BomberMove(ReentrantLock[][] board, int size) {
        this.board = board;
        this.size = size - 1;
    }

    public Cell move(Cell source) throws InterruptedException {
        int curX = source.getPosX();
        int curY = source.getPosY();
        Cell dest = new Cell(curX, curY);
        boolean moved = false;
        while (!moved) {
            if (hor) {
                if (curY > size - 1 && !highY) {
                    deltaY *= -1;
                    hor = false;
                    highY = true;
                }
                if (curY < 1 && highY) {
                    deltaY *= -1;
                    hor = false;
                    highY = false;
                }
            }
            if (!hor) {
                if (curX > size - 1 && !highX) {
                    deltaX *= -1;
                    hor = true;
                    highX = true;
                }
                if (curX < 1 && highX) {
                    deltaX *= -1;
                    hor = true;
                    highX = false;
                }
            }
            if (hor) {
                if (board[curX][curY + deltaY].tryLock(500, TimeUnit.MILLISECONDS)) {
                    moved = true;
                    dest.setPosY(curY + deltaY);

                }
            } else {
                if (board[curX + deltaX][curY].tryLock(500, TimeUnit.MILLISECONDS)) {
                    moved = true;
                    dest.setPosX(curX + deltaX);
                }
            }
        }
        if (moved) {
            board[curX][curY].unlock();
        }
        return dest;
    }
}
