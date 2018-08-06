package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * Movement logic class.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.2$
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
        int deltaX = 1;
        int deltaY = 1;
        double posX;
        double posY;
        while (!Thread.interrupted()) {
            posX = this.rect.getX();
            posY = this.rect.getY();
            if (posX < 1 || posX > 299) {
                deltaX *= -1;
            }
            if (posY < 1 || posY > 299) {
                deltaY *= -1;
            }
            this.rect.setX(posX + deltaX);
            this.rect.setY(posY + deltaY);
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}