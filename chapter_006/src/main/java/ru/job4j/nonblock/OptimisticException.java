package ru.job4j.nonblock;

public class OptimisticException extends RuntimeException {
    public OptimisticException(String msg) {
        super(msg);
    }
}
