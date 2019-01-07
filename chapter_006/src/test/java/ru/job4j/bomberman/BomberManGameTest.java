package ru.job4j.bomberman;

import org.junit.Test;

public class BomberManGameTest {
    @Test
    public void whenMovesAndRestedInBorderThenNextMove() throws InterruptedException {
        BomberManGame game = new BomberManGame(5, 2, 10, 10);
        game.startGame();
    }
}