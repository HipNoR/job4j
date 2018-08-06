package ru.job4j.synchro;

import org.junit.Test;


import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class UserStorageTest {

    @Test
    public void whenAddTwoThenSizeIsTwo() {
        UserStorage st = new UserStorage();
        st.add(new User(1, 10));
        st.add(new User(2, 10));
        assertThat(st.size(), is(2));
    }

    @Test
    public void whenInsertSameIdThenNotInserted() {
        UserStorage st = new UserStorage();
        st.add(new User(1, 10));
        st.add(new User(1, 20));
        assertThat(st.size(), is(1));
    }

    @Test
    public void whenUpdateUserThenUpdated() {
        UserStorage st = new UserStorage();
        st.add(new User(1, 10));
        st.update(new User(1, 20));
        assertThat(st.searchUserById(1).getAmount(), is(20));
    }

    @Test
    public void whenDeleteUserThenDeleted() {
        UserStorage st = new UserStorage();
        st.add(new User(1, 10));
        st.add(new User(2, 10));
        st.delete(new User(1, 20));
        assertThat(st.size(), is(1));
    }

    @Test
    public void whenTransferAndEnouthAmountThenTrue() {
        UserStorage st = new UserStorage();
        st.add(new User(1, 100));
        st.add(new User(2, 100));
        assertThat(st.transfer(1, 2, 50), is(true));
        assertThat(st.searchUserById(1).getAmount(), is(50));
        assertThat(st.searchUserById(2).getAmount(), is(150));
    }

    @Test
    public void threadTest() throws InterruptedException {
        UserStorage st = new UserStorage();

        Thread one = new StorageAdd(st);
        Thread two = new StorageDel(st);
        one.start();
        two.start();
        one.join();
        two.join();
        System.out.println("finish");
        st.printStorage();
    }

    private class StorageAdd extends Thread {
        private final UserStorage storage;

        private StorageAdd(final UserStorage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            this.storage.add(new User(1, 100));
            this.storage.add(new User(2, 50));
            this.storage.add(new User(3, 30));
            System.out.println("Storage adds runs.");
            storage.printStorage();
        }
    }

    private class StorageDel extends Thread {
        private final UserStorage storage;

        private StorageDel(final UserStorage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            this.storage.delete(new User(3, 50));
            System.out.println("Storage delete runs.");
            storage.printStorage();
        }
    }

}