package ru.job4j.chess.exceptions;

public class OccupiedWayException extends RuntimeException {

    public OccupiedWayException(String msg) {
        super(msg);
    }
}
