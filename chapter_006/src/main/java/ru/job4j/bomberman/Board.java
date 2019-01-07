package ru.job4j.bomberman;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The game board class.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 08.01.2019
 */
public class Board {
    private static final Logger LOG = LogManager.getLogger(Board.class.getName());
    /**
     * 2d array of the reentrant locks = board of the game.
     */
    private final ReentrantLock[][] board;
    private final int size;

    public Board(final int size) {
        this.size = size;
        this.board = new ReentrantLock[size][size];
        for (int out = 0; out < size; out++) {
            for (int in = 0; in < size; in++) {
                board[out][in] = new ReentrantLock();
            }
        }
    }

    /**
     * The Method moves the unit (Bomber or monster) from point to point.
     * @param source the point from which unit will move.
     * @param dest the point at which unit will move.
     * @return true or false of this turn.
     * @throws InterruptedException if tryLock is was interrupted.
     */
    public boolean move(Cell source, Cell dest) throws InterruptedException {
        int curX = source.getX();
        int curY = source.getY();
        int destX = dest.getX();
        int destY = dest.getY();
        LOG.info("Try {} to {}", source, dest);
        boolean moved = false;
        if (!(destX < 0 || destX > size - 1 || destY < 0 || destY > size - 1)) {
            if (board[destX][destY].tryLock(500, TimeUnit.MILLISECONDS)) {
                moved = true;
                board[curX][curY].unlock();
                LOG.info("Successful move");
            } else {
                LOG.info("Occupied way, failure.");
            }
        } else {
            LOG.info("Border on the way, failure.");
        }
        return moved;
    }

    public boolean lockCell(Cell cell) throws InterruptedException {
        return this.board[cell.getX()][cell.getY()].tryLock(10, TimeUnit.MILLISECONDS);
    }

    public int getSize() {
        return size;
    }
}
