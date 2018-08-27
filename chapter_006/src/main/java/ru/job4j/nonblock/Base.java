package ru.job4j.nonblock;

/**
 * Simple data class.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 14.08.2018
 */
public class Base {
    private final int id;
    private int version = 0;
    private String name = "";

    public Base(final int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void versionUpdate() {
        this.version++;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("Name updated to " + name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Base base = (Base) o;
        if (id != base.id) {
            return false;
        }
        return version == base.version;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + version;
        return result;
    }

    @Override
    public String toString() {
        return String.format("Base id: %s, ver: %s, name: %s.", id, version, name);
    }
}
