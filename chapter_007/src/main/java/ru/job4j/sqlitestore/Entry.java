package ru.job4j.sqlitestore;

import javax.xml.bind.annotation.XmlRootElement;

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

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object obj) {
        boolean valid = false;
        if (obj != null) {
            if (this == obj) {
                valid = true;
            }
            if (!valid && getClass() == obj.getClass()) {
                Entry entry = (Entry) obj;
                valid = this.field == entry.field;
            }
        }
        return valid;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
