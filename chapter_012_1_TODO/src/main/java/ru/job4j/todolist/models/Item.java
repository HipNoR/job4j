package ru.job4j.todolist.models;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Entity model class.
 * The class describes the task, the unique identifier, the description of the task,
 * the date of creation and the state of execution.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 16.01.2019
 */
public class Item {
    private long id;
    private String descript;
    private Timestamp created;
    private boolean done;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return id == item.id
                && done == item.done
                && Objects.equals(descript, item.descript)
                && Objects.equals(created, item.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descript, created, done);
    }

    @Override
    public String toString() {
        return String.format("Item{id=%s, description=%s, created=%s, done=%s}", id, descript, created, done);
    }
}
