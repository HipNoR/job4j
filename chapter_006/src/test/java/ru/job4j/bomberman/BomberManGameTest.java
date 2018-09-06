package ru.job4j.bomberman;

import org.junit.Test;

public class BomberManGameTest {
    @Test
    public void whenMovesAndRestedInBorderThenNextMove()  {
        Move[] moves = new Move[]{new Move(0, 1), new Move(0, 1), new Move(1, 0), new Move(0, 1), new Move(0, -1)};
        BomberManGame game = new BomberManGame(3, moves);
        try {
            game.startGame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}