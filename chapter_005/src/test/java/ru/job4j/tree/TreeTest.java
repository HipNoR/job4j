package ru.job4j.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TreeTest {
    Tree<Integer> tree;

    @Before
    public void prepare() {
        tree = new Tree<>(1);
        tree.add(1, 2);
    }


    @Test
    public void when6ElFindLastThen6() {
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        assertThat(tree.add(1, 2), is(false));
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenIterateThenTrue() {
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(3, 5);
        Iterator itr = tree.iterator();
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(1));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(2));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(3));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(4));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(5));
        assertThat(itr.hasNext(), is(false));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenIterateAndModifiedThenException() {
        Iterator itr = tree.iterator();
        tree.add(2, 3);
        itr.hasNext();
    }

    @Test
    public void ifBinaryThenTrue() {
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        tree.add(3, 7);
        assertThat(tree.isBinary(), is(true));
    }

    @Test
    public void whenMoreThanTwoChildsThenNotBinary() {
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(2, 6);
        assertThat(tree.isBinary(), is(false));
    }
}
