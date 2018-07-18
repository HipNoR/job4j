package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.GregorianCalendar;
import java.util.Iterator;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class SimpleHashMapTest {
    SimpleHashMap<User, String> map;

    @Before
    public void prepareForTest() {
        map = new SimpleHashMap<>();
    }

    @Test
    public void whenAddEqualsThenFalse() {
        User first = new User("Roman", 0, new GregorianCalendar(1984, 9, 15));
        User second = new User("Roman", 0, new GregorianCalendar(1984, 9, 15));
        assertThat(map.insert(first, "first"), is(true));
        assertThat(map.insert(second, "second"), is(false));
    }

    @Test
    public void whenAddNonEqualsThenTrue() {
        User first = new User("Roman", 0, new GregorianCalendar(1984, 9, 15));
        User second = new User("Roman", 1, new GregorianCalendar(1984, 9, 15));
        assertThat(map.insert(first, "first"), is(true));
        assertThat(map.insert(second, "second"), is(true));
    }

    @Test
    public void whenGetByKeyAndItExistedThenGetIt() {
        User first = new User("Roman", 0, new GregorianCalendar(1984, 9, 15));
        User second = new User("Roman", 1, new GregorianCalendar(1984, 9, 15));
        map.insert(first, "first");
        map.insert(second, "second");
        assertThat(map.get(second), is("second"));
        map.get(new User("one", 10, new GregorianCalendar(1990, 10, 1)));
    }

    @Test
    public void whenGetNotExistedThanNull() {
        assertNull(map.get(new User("one", 10, new GregorianCalendar(1990, 10, 1))));
    }

    @Test
    public void resizeTest() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        assertThat(map.capacity(), is(16));
        for (int index = 1; index < 20; index++) {
            map.insert("key" + index + "that", "inserted" + index);
        }
        assertThat(map.capacity(), is(32));
    }

    @Test
    public void whenDeleteExistedThanTrue() {
        map = new SimpleHashMap<>();
        User first = new User("Roman", 0, new GregorianCalendar(1984, 9, 15));
        User second = new User("Oleg", 0, new GregorianCalendar(1984, 9, 15));
        map.insert(first, "first");
        map.insert(second, "second");
        assertThat(map.delete(second), is(true));
    }

    @Test
    public void whenDeleteNotExistedThenFalse() {
        assertFalse(map.delete(new User("one", 10, new GregorianCalendar(1990, 10, 1))));
    }

    @Test
    public void whenIterateThanTrue() {
        User first = new User("Roman", 0, new GregorianCalendar(1984, 9, 15));
        User second = new User("Oleg", 0, new GregorianCalendar(1984, 9, 15));
        map.insert(first, "first");
        map.insert(second, "second");
        Iterator itr = map.iterator();
        assertThat(itr.hasNext(), is(true));
        System.out.println(itr.next());
        assertThat(itr.hasNext(), is(true));
        System.out.println(itr.next());
        assertThat(itr.hasNext(), is(false));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenIterateAndModifiedThanException() {
        User first = new User("Roman", 0, new GregorianCalendar(1984, 9, 15));
        User second = new User("Oleg", 0, new GregorianCalendar(1984, 9, 15));
        map.insert(first, "first");
        map.insert(second, "second");
        Iterator itr = map.iterator();
        assertThat(itr.hasNext(), is(true));
        map.insert(new User("one", 10, new GregorianCalendar(1990, 10, 1)), "New");
        itr.hasNext();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenIterateAndModifiedThanExceptionSecondTest() {
        User first = new User("Roman", 0, new GregorianCalendar(1984, 9, 15));
        User second = new User("Oleg", 0, new GregorianCalendar(1984, 9, 15));
        map.insert(first, "first");
        map.insert(second, "second");
        Iterator itr = map.iterator();
        map.insert(new User("one", 10, new GregorianCalendar(1990, 10, 1)), "New");
        itr.next();
    }
}