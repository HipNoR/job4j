package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        // создаём StubInput и добавляем две заявки
        Input input = new StubInput(new String[]{"0", "test name", "desc", "0", "test name2", "desc2", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что первый элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findAll()[1].getName(), is("test name2"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item("test2", "desc2"));
        //создаём StubInput с последовательным выбором меню редактирование заявки
        Input input = new StubInput(new String[]{"2", item.getId(), "test name", "desc", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что в элементе item имя заменилось на введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }

    @Test
    public void whenDeleteOneOfThreeThenTrackerContainsTwo() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявки
        Item first = tracker.add(new Item("test1", "desc1"));
        Item second = tracker.add(new Item("test2", "desc2"));
        Item third = tracker.add(new Item("test3", "desc3"));
        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"3", second.getId(), "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что первый элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findAll()[1].getName(), is("test3"));
    }
}
