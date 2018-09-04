package ru.job4j.bomberman;

import org.junit.Test;

public class BomberManGameTest {
    @Test
    public void firstTest()  {
        BomberManGame game = new BomberManGame(5, 10);
        try {
            game.startGame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}