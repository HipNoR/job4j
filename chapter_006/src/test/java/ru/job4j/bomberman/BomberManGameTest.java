package ru.job4j.bomberman;

import org.junit.Test;

public class BomberManGameTest {
    @Test
    public void whenMovesAndRestedInBorderThenNextMove()  {
        String[] moves = {"R", "R", "R", "D", "L"};
        BomberManGame game = new BomberManGame(3, moves);
        try {
            game.startGame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}