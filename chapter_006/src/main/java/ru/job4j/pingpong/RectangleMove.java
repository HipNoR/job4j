package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * Movement logic class.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 30.07.2018
 */
public class RectangleMove implements Runnable {
    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        int moveX = 1;
        int moveY = 1;
        double posX;
        double posY;
        while (!Thread.interrupted()) {
            posX = this.rect.getX();
            posY = this.rect.getY();
            if (posX == 300) {
                moveX = -1;
            }
            if (posX == 0) {
                moveX = 1;
            }
            if (posY == 300) {
                moveY = -1;
            }
            if (posY == 0) {
                moveY = 1;
            }
            this.rect.setX(posX + moveX);
            this.rect.setY(posY + moveY);
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}