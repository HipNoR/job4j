package ru.job4j.bomberman;

import java.util.concurrent.locks.ReentrantLock;

/**
 * BomberMan game.
 * The Bomber passes through the field and turns clockwise, if there is any obstacle.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 03.09.2018
 */
public class BomberManGame {
    private final ReentrantLock[][] board;
    private final BomberMan bomber;
    private final BomberMove mover;
    private int timeCount;
    private boolean isStopped = false;

    /**
     * Constructor.
     * By default the Bomber starts the game from (0, 0) cell.
     * @param size of the field.
     *             The field is square (size*size).
     * @param timeCount the number of moves, when it reaches zero, the game will stop. (for tests)
     */
    public BomberManGame(int size, int timeCount) {
        board = new ReentrantLock[size][size];
        for (int out = 0; out < size; out++) {
            for (int in = 0; in < size; in++) {
                board[out][in] = new ReentrantLock();
            }
        }
        mover = new BomberMove(board, size);
        this.bomber = new BomberMan(0, 0);
        this.timeCount = timeCount;
        System.out.println(bomber);
    }

    /**
     * The Method moves the Bomber from point to point.
     * @param source the point from which Bomber will move.
     * @param dest the point at which Bomber will move.
     */
    public void move(Cell source, Cell dest) {
        bomber.setPosition(dest);
        System.out.println(String.format("Moved from %s to %s", source, dest));
        if (--timeCount == 0) {
            stopGame();
        }
    }

    /**
     * Starts the game.
     * Starts the Thread - BomberMove.
     * @throws InterruptedException
     */
    public void startGame() throws InterruptedException {
        Thread move = new Thread("BomberMove") {

            @Override
            public void run() {
                board[bomber.getPosition().getPosX()][bomber.getPosition().getPosY()].lock();
                while (!isStopped) {
                    try {
                        move(bomber.getPosition(), mover.move(bomber.getPosition()));
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };
        move.start();
        move.join();
    }

    /**
     * Stops the game.
     */
    public void stopGame() {
        System.out.println(String.format("%s stops the bomber", Thread.currentThread().getName()));
        Thread.currentThread().interrupt();
        isStopped = true;

    }
}
