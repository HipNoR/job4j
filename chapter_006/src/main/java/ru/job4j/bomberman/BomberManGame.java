package ru.job4j.bomberman;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * BomberMan game.
 * The Bomber and monsters passes through the field by random steps.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.4$
 * @since 0.1
 * 03.09.2018
 */
public class BomberManGame {
    private static final Logger LOG = LogManager.getLogger(BomberManGame.class.getName());

    /**
     * Board with locks.
     */
    private final Board board;
    /**
     * The Hero.
     */
    private final BomberMan bomber;
    /**
     * Monsters array.
     */
    private final Monster[] monsters;
    /**
     * The game status.
     */
    private boolean isRunning = true;
    /**
     * For tests field, the number of movements of the bomber until the closure of the program.
     */
    private int moves;


    /**
     * By default the Bomber starts the game from (0, 0) cell.
     * @param size of the field.The field is square (size*size).
     * @param moves number of BomberMan steps.
     * @param monsters number of monster on the board.
     * @param barriers number of barriers on the board.
     */
    public BomberManGame(int size,  int monsters, int barriers, int moves) {
        Cell startPos = new Cell(0, 0);
        this.board = new Board(size);
        this.bomber = new BomberMan(startPos.getX(), startPos.getY());
        this.monsters = new Monster[monsters];
        this.moves = moves;
        try {
            createBarriers(barriers, startPos);
        } catch (InterruptedException e) {
            LOG.error("Error interrupted", e);
        }
        LOG.info(bomber);
    }

    /**
     * Method returns random cell on the board.
     * @return cell.
     */
    private Cell randomCell() {
        return new Cell((int) (Math.random() * board.getSize()), (int) (Math.random() * board.getSize()));
    }

    /**
     * Creates random barriers on the board.
     * If the starting position of the bomber falls out, it searches for another cell.
     * @param barriers number of the barriers.
     * @throws InterruptedException if interrupted.
     */
    private void createBarriers(int barriers, Cell start) throws InterruptedException {
        Cell temp;
        for (int index = 0; index != barriers; index++) {
            do {
                do {
                    temp = randomCell();
                } while (temp.equals(start));
            } while (!board.lockCell(temp));
        }
    }

    /**
     * Creates monster on board.
     * @throws InterruptedException when interrupted.
     */
    private void monsterCreate() throws InterruptedException {
        Cell temp;
        for (int index = 0; index != monsters.length; index++) {
            do {
                temp = randomCell();
            } while (!board.lockCell(temp));
            monsters[index] = new Monster(temp, index);
        }
    }

    /**
     * Creates a random next destination.
     * If the found step is out of the field, it looks for another step.
     * @param source the point from which unit will move.
     * @return next step.
     */

    private Cell nextStep(Cell source) {
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
        Cell dest = new Cell(source.getX() + deltaX, source.getY() + deltaY);
        return ((dest.getX() < 0 || dest.getY() < 0)
                || (dest.getX() >= board.getSize() || dest.getY() >= board.getSize()))
                ? nextStep(source) : dest;
    }

    /**
     * Starts the game.
     * Starts the Thread - BomberMove.
     * @throws InterruptedException if thread was interrupted.
     */
    public void startGame() throws InterruptedException {
        Thread bomberMove = new Thread("Bomber") {

            @Override
            public void run() {
                Cell next;
                try {
                    board.lockCell(bomber.getPosition());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (isRunning) {
                    try {
                        next = nextStep(bomber.getPosition());
                        if (board.move(bomber.getPosition(), next)) {
                            bomber.setPosition(next);
                        }
                        moves--;
                        sleep(1000);
                    } catch (InterruptedException e) {
                        LOG.error("BomberMoveERROR", e);
                    }
                    if (moves == 0) {
                        isRunning = false;
                    }
                }
                LOG.info("THE END!");
            }
        };

        Thread monstersMove = new Thread("Monsters") {
            @Override
            public void run() {
                Monster temp;
                Cell next;
                int monsterOrder = 0;
                try {
                    monsterCreate();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (isRunning) {
                    temp = monsters[monsterOrder++];
                    if (monsterOrder == monsters.length) {
                        monsterOrder = 0;
                    }
                    next = nextStep(temp.getPosition());
                    try {
                        if (board.move(temp.getPosition(), next)) {
                            temp.setPosition(next);
                        }
                        sleep(3000);
                    } catch (InterruptedException e) {
                        LOG.error("MonsterMoveERROR", e);
                    }
                }
            }
        };
        bomberMove.start();
        Thread.sleep(10); //provides a guarantee of locking the starting position of the Bomberman.
        monstersMove.start();
        bomberMove.join();
    }
}
