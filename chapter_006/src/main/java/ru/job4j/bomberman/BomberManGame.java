package ru.job4j.bomberman;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * BomberMan game.
 * The Bomber passes through the field and turns clockwise, if there is any obstacle.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.4$
 * @since 0.1
 * 03.09.2018
 */
public class BomberManGame {
    private final ReentrantLock[][] board;
    private final BomberMan bomber;
    private final ArrayList<Monster> monsters = new ArrayList<>();
    private final int monstersNum;
    private int monsterOrder;
    private int moves;
    private int size;
    private boolean isRunning = true;

    /**
     * Constructor.
     * By default the Bomber starts the game from (0, 0) cell.
     * @param size
     * of the field.
     *             The field is square (size*size).
     * @param moves number of BomberMan steps.
     * @param monsters number of monster on the board.
     * @param barriers number of barriers on the board.
     */
    public BomberManGame(int size, int moves, int monsters, int barriers) {
        this.board = new ReentrantLock[size][size];
        this.monstersNum = monsters;
        this.moves = moves;
        this.size = size;
        for (int out = 0; out < size; out++) {
            for (int in = 0; in < size; in++) {
                board[out][in] = new ReentrantLock();
            }
        }
        this.bomber = new BomberMan(0, 0);
        try {
            createBarriers(barriers);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        System.out.println(String.format("Bomber moving from %s to %s", source, dest));
        boolean moved = false;
        if (!(destX < 0 || destX > size - 1 || destY < 0 || destY > size - 1)) {
            if (board[destX][destY].tryLock(500, TimeUnit.MILLISECONDS)) {
                moved = true;
                board[curX][curY].unlock();
                bomber.setPosition(dest);
                System.out.println("Bomber - Success!");
            } else {
                System.out.println("Bomber - Occupied way");
            }
        } else {
            System.out.println("Bomber - Border on move");
        }
        if (moves == 0) {
            isRunning = false;
            System.out.println("No more steps.");
        }
        return moved;
    }

    public boolean monsterMove(Monster current, Cell dest) throws InterruptedException {
        Cell position = current.getPosition();
        int curX = position.getPosX();
        int curY = position.getPosY();
        int destX = dest.getPosX();
        int destY = dest.getPosY();
        System.out.println(String.format("Monster#%s moves from %s to %s", current.getNum(), position, dest));
        boolean moved = false;
        if (!(destX < 0 || destX > size - 1 || destY < 0 || destY > size - 1)) {
            if (board[destX][destY].tryLock(500, TimeUnit.MILLISECONDS)) {
                moved = true;
                board[curX][curY].unlock();
                current.setPosition(dest);
                System.out.println(String.format("Monster#%s - Success!", current.getNum()));
            } else {
                System.out.println(String.format("Monster#%s - Occupied way!", current.getNum()));
            }
        } else {
            System.out.println(String.format("Monster#%s - Border on move!", current.getNum()));
        }
        return moved;
    }

    /**
     * Method returns random cell on the board.
     * @return cell.
     */
    private Cell randomCell() {
        return new Cell((int) (Math.random() * size), (int) (Math.random() * size));
    }

    /**
     * Creates random barriers on the board.
     * @param barriers number of the barriers.
     * @throws InterruptedException if interrupted.
     */
    private void createBarriers(int barriers) throws InterruptedException {
        Cell temp;
        for (int index = 0; index != barriers; index++) {
            do {
                temp = randomCell();
            } while (!board[temp.getPosX()][temp.getPosY()].tryLock(10, TimeUnit.MILLISECONDS));
        }
    }

    /**
     * Creates monster on board.
     * @param number of monster on board.
     * @throws InterruptedException when interrupted.
     */
    private void monsterCreate(int number) throws InterruptedException {
        Cell temp;
        for (int index = 0; index != number; index++) {
            do {
                temp = randomCell();
            } while (!board[temp.getPosX()][temp.getPosY()].tryLock(10, TimeUnit.MILLISECONDS));
            monsters.add(new Monster(temp, index));
        }
    }

    /**
     * Creates a random next destination.
     * @param source the point from which unit will move.
     * @param monster monster flag.
     * @return next step.
     */

    private Cell nextStep(Cell source, boolean monster) {
        int deltaX = 0;
        int deltaY = 0;
        int random = (int) (Math.random() * 100);
        if (random < 25) {
            deltaY = 1;
        }  else if (random < 50) {
            deltaX = 1;
        }  else if (random < 75) {
            deltaY = -1;
        } else if (random < 100) {
            deltaX = -1;
        }
        if (!monster) {
            this.moves--;
        }
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
                Cell currentPos = bomber.getPosition();
                board[currentPos.getPosX()][currentPos.getPosY()].lock();
                while (isRunning) {
                    try {
                        currentPos = bomber.getPosition();
                        move(currentPos, nextStep(currentPos, false));
                        sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("BomberMoveERROR");
                    }
                }
                System.out.println("THE END!");
            }
        };

        Thread monstersMove = new Thread("MonsterMove") {
            @Override
            public void run() {
                try {
                    monsterCreate(monstersNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Monster temp;
                while (isRunning) {
                    temp = monsters.get(monsterOrder++);
                    if (monsterOrder == monstersNum) {
                        monsterOrder = 0;
                    }
                    try {
                        monsterMove(temp, nextStep(temp.getPosition(), true));
                        sleep(5000);
                    } catch (InterruptedException e) {
                        System.out.println("MonsterMoveERROR");

                    }
                }
            }
        };
        move.start();
        monstersMove.start();
        move.join();
    }
}
