package ru.job4j.bank.exceptions;

public class NoSuchAccountException extends RuntimeException {

    public NoSuchAccountException(String message) {
        super(message);
    }
}
