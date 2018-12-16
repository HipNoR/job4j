package ru.job4j.cinema.persistence;

import java.io.Serializable;
import java.util.Objects;

/**
 * Cinema seat model.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 09.12.2018
 */
public class Seat implements Serializable {
    private int row;
    private int seat;
    private int reserved;

    public Seat(int row, int seat) {
        this.row = row;
        this.seat = seat;
    }

    public Seat(int row, int seat, int reserved) {
        this.row = row;
        this.seat = seat;
        this.reserved = reserved;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    @Override
    public String toString() {
        return String.format("Seat{row=%s, seat=%s, reserved=%s}", row, seat, reserved);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Seat seat1 = (Seat) o;
        return row == seat1.row
                && seat == seat1.seat
                && reserved == seat1.reserved;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, seat, reserved);
    }
}
