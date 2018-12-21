package ru.job4j.tracker;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    Timestamp stub = new Timestamp(System.currentTimeMillis());

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        RamTracker  tracker = new RamTracker();
        Item item = new Item("1", "test1", "testDescription", stub);
        tracker.add(item);
        assertThat(tracker.findAll().get(0), is(item));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        RamTracker  tracker = new RamTracker();
        Item previous = new Item("1", "test1", "testDescription", stub);
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("1", "test2", "testDescription2", stub);
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenDeleteThenReturnNewItem() {
        RamTracker  tracker = new RamTracker();
        // Создаем три заявки и добавляем их все в трекер. Каждая получает уникальный id.
        Item first = new Item("1", "test1", "testDescription1", stub);
        tracker.add(first);
        Item second = new Item("2", "test2", "testDescription2", stub);
        tracker.add(second);
        Item third = new Item("3", "test3", "testDescription3", stub);
        tracker.add(third);
        // Удаляем второй объект Item по его id.
        tracker.delete(second.getId());
        // Создаем ожидаемый массив класса Item.
        List<Item> expected = new ArrayList<>();
        expected.add(first);
        expected.add(third);
        // Проверяем, что итоговый и ожидаемый массив равны.
        assertThat(tracker.findAll(), is(expected));
    }

    @Test
    public void whenDeleteAndCantFindThenReturnNewItem() {
        RamTracker  tracker = new RamTracker();
        // Создаем три заявки и добавляем их все в трекер. Каждая получает уникальный id.
        Item first = new Item("1", "test1", "testDescription1", stub);
        tracker.add(first);
        Item second = new Item("2", "test2", "testDescription2", stub);
        tracker.add(second);
        Item third = new Item("3", "test3", "testDescription3", stub);
        tracker.add(third);
        // Удаляем второй объект Item по его id.
        tracker.delete(third.getId());
        tracker.delete(third.getId());
        tracker.delete(third.getId());
        // Создаем ожидаемый массив класса Item.
        List<Item> expected = new ArrayList<>();
        expected.add(first);
        expected.add(second);
        // Проверяем, что итоговый и ожидаемый массив равны.
        assertThat(tracker.findAll(), is(expected));
    }

    @Test
    public void whenFindAllThenReturnAll() {
        RamTracker  tracker = new RamTracker();
        // Создаем три заявки и добавляем их все в трекер. Каждая получает уникальный id.
        Item first = new Item("1", "test1", "testDescription1", stub);
        tracker.add(first);
        Item second = new Item("2", "test2", "testDescription2", stub);
        tracker.add(second);
        Item third = new Item("3", "test3", "testDescription3", stub);
        tracker.add(third);
        // Создаем ожидаемый массив класса Item.
        List<Item> expected = new ArrayList<>();
        expected.add(first);
        expected.add(second);
        expected.add(third);
        // Проверяем, что массив, передаваемый через метод findAll и ожидаемый массив равны.
        assertThat(tracker.findAll(), is(expected));
    }

    @Test
    public void whenFindByNameThenReturnByName() {
        RamTracker  tracker = new RamTracker();
        // Создаем три заявки и добавляем их все в трекер. Каждая получает уникальный id, две имеют одинаковое имя.
        Item first = new Item("1", "test1", "testDescription1", stub);
        tracker.add(first);
        Item second = new Item("2", "test2", "testDescription2", stub);
        tracker.add(second);
        Item third = new Item("3", "test2", "testDescription3", stub);
        tracker.add(third);
        // Создаем ожидаемый массив класса Item из двух элементов с одинаковым именем.
        List<Item> expected = new ArrayList<>();
        expected.add(second);
        expected.add(third);
        // Проверяем, что массив, передаваемый через метод findAll и ожидаемый массив равны.
        assertThat(tracker.findByName("test2"), is(expected));
    }

    @Test
    public void whenFindByIdThenReturnExpected() {
        RamTracker  tracker = new RamTracker();
        // Создаем три заявки и добавляем их все в трекер. Каждая получает уникальный id.
        Item first = new Item("1", "test1", "testDescription1", stub);
        tracker.add(first);
        Item second = new Item("2", "test2", "testDescription2", stub);
        tracker.add(second);
        Item third = new Item("3", "test3", "testDescription3", stub);
        tracker.add(third);
        // Создаем объект класса Item и присваеваем ему id одного из элементов добавленных в трекер.
        Item expected = new Item("2", "test2", "testDescription2", stub);
        // Сравниваем, что имя возвращенного элемента методом findById равно имени ожидаемого элемента.
        assertThat(tracker.findById(second.getId()).getName(), is(expected.getName()));
    }

    @Test
    public void whenFindByIdAndCantFindThenReturnNull() {
        RamTracker  tracker = new RamTracker();
        // Создаем три заявки и добавляем их все в трекер. Каждая получает уникальный id.
        Item first = new Item("1", "test1", "testDescription1", stub);
        tracker.add(first);
        Item second = new Item("2", "test2", "testDescription2", stub);
        tracker.add(second);
        Item third = new Item("3", "test3", "testDescription3", stub);
        tracker.add(third);
        // Сравниваем, что если поиск не нашел элемента, то метод возвращает null.
        assertNull(tracker.findById("10"));
    }
}

