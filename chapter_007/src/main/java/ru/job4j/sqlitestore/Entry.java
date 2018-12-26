package ru.job4j.sqlitestore;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * The database item class for converting a database to XML.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 09.10.2018
 */

@XmlRootElement
public class Entry {
    private int field;

    public Entry() {
    }

    public Entry(int i) {
        this.field = i;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entry entry = (Entry) o;
        return field == entry.field;
    }

    @Override
    public int hashCode() {
        return Objects.hash(field);
    }

    @Override
    public String toString() {
        return String.format("Entry{field=%s}", field);
    }
}
