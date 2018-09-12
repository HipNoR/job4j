package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * BomberMan game.
 * The Bomber passes through the field and turns clockwise, if there is any obstacle.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.3$
 * @since 0.1
 * 03.09.2018
 */
public class BomberManGame {
    private final ReentrantLock[][] board;
    private final BomberMan bomber;
    private final String[] moves;
    private int moveCount = 0;
    private int border;
    private boolean isRunning = true;

    /**
     * Constructor.
     * By default the Bomber starts the game from (0, 0) cell.
     * @param size of the field.
     *             The field is square (size*size).
     */
    public BomberManGame(int size, String[] moves) {
        this.board = new ReentrantLock[size][size];
        this.moves = moves;
        this.border = size - 1;
        for (int out = 0; out < size; out++) {
            for (int in = 0; in < size; in++) {
                board[out][in] = new ReentrantLock();
            }
        }
        this.bomber = new BomberMan(0, 0);
        System.out.println(bomber);
    }

    /**
     * The Method moves the Bomber from point to point.
     * @param source the point from which Bomber will move.
     * @param dest the point at which Bomber will move.
     * @return true or false of this turn.
     * @throws InterruptedException if tryLock is was interrupted.
     */
    public boolean move(Cell source, Cell dest) throws InterruptedException {
        int curX = source.getPosX();
        int curY = source.getPosY();
        int destX = dest.getPosX();
        int destY = dest.getPosY();
        System.out.println(String.format("Trying to move from %s to %s", source, dest));
        boolean moved = false;
        if (!(destX < 0 || destX > border || destY < 0 || destY > border)) {
            if (board[destX][destY].tryLock(500, TimeUnit.MILLISECONDS)) {
                moved = true;
                board[curX][curY].unlock();
                bomber.setPosition(dest);
                System.out.println("Success!");
            } else {
                System.out.println("Hindrance, next move.");
            }
        } else {
            System.out.println("Wrong way!");
        }
        if (moveCount == moves.length) {
            isRunning = false;
            System.out.println("No more steps.");
        }
        return moved;
    }

    /**
     * Finds the destination point.
     * @param source the point from which Bomber will move.
     * @return dest the point at which Bomber will move.
     */
    private Cell nextStep(Cell source) {
        String nextMove = moves[moveCount++];
        int deltaX = 0;
        int deltaY = 0;
        if (nextMove.equals("R")) {
            deltaY = 1;
        } else if (nextMove.equals("L")) {
            deltaY = -1;
        } else if (nextMove.equals("D")) {
            deltaX = 1;
        } else if (nextMove.equals("U")) {
            deltaX = -1;
        }
        System.out.println(String.format("%s step out of %s", moveCount, moves.length));
        return new Cell(source.getPosX() + deltaX, source.getPosY() + deltaY);
    }

    /**
     * Starts the game.
     * Starts the Thread - BomberMove.
     * @throws InterruptedException if thread was interrupted.
     */
    public void startGame() throws InterruptedException {
        Thread move = new Thread("BomberMove") {

            @Override
            public void run() {
                board[bomber.getPosition().getPosX()][bomber.getPosition().getPosY()].lock();
                while (isRunning) {
                    try {
                        move(bomber.getPosition(), nextStep(bomber.getPosition()));
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("THE END!");
            }
        };
        move.start();
        move.join();
    }
}
