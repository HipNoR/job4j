package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2", "testDescription2", 1234L);
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenDeleteThenReturnNewItem() {
        Tracker tracker = new Tracker();
        // Создаем три заявки и добавляем их все в трекер. Каждая получает уникальный id.
        Item first = new Item("test1", "testDescription1", 123L);
        tracker.add(first);
        Item second = new Item("test2", "testDescription2", 234L);
        tracker.add(second);
        Item third = new Item("test3", "testDescription3", 456L);
        tracker.add(third);
        // Удаляем второй объект Item по его id.
        tracker.delete(second.getId());
        // Создаем ожидаемый массив класса Item.
        Item[] expected = new Item[2];
        expected[0] = first;
        expected[1] = third;
        // Проверяем, что итоговый и ожидаемый массив равны.
        assertThat(tracker.findAll(), is(expected));

    }

    @Test
    public void whenFindAllThenReturnAll() {
        Tracker tracker = new Tracker();
        // Создаем три заявки и добавляем их все в трекер. Каждая получает уникальный id.
        Item first = new Item("test1", "testDescription1", 123L);
        tracker.add(first);
        Item second = new Item("test2", "testDescription2", 234L);
        tracker.add(second);
        Item third = new Item("test3", "testDescription3", 456L);
        tracker.add(third);
        // Создаем ожидаемый массив класса Item.
        Item[] expected = new Item[3];
        expected[0] = first;
        expected[1] = second;
        expected[2] = third;
        // Проверяем, что массив, передаваемый через метод findAll и ожидаемый массив равны.
        assertThat(tracker.findAll(), is(expected));
    }

    @Test
    public void whenFindByNameThenReturnByName() {
        Tracker tracker = new Tracker();
        // Создаем три заявки и добавляем их все в трекер. Каждая получает уникальный id, две имеют одинаковое имя.
        Item first = new Item("test1", "testDescription1", 123L);
        tracker.add(first);
        Item second = new Item("test2", "testDescription2", 234L);
        tracker.add(second);
        Item third = new Item("test2", "testDescription3", 456L);
        tracker.add(third);
        // Создаем ожидаемый массив класса Item из двух элементов с одинаковым именем.
        Item[] expected = new Item[2];
        expected[0] = second;
        expected[1] = third;
        // Проверяем, что массив, передаваемый через метод findAll и ожидаемый массив равны.
        assertThat(tracker.findByName("test2"), is(expected));
    }

    @Test
    public void whenFindByIdThenReturnItem() {
        Tracker tracker = new Tracker();
        // Создаем три заявки и добавляем их все в трекер. Каждая получает уникальный id.
        Item first = new Item("test1", "testDescription1", 123L);
        tracker.add(first);
        Item second = new Item("test2", "testDescription2", 234L);
        tracker.add(second);
        Item third = new Item("test3", "testDescription3", 456L);
        tracker.add(third);
        // Создаем объект класса Item и присваеваем ему id одного из элементов добавленных в трекер.
        Item expected = new Item("test2", "testDescription2", 234L);
        expected.setId(second.getId());
        // Сравниваем, что имя возвращенного элемента методом findById равно имени ожидаемого элемента.
        assertThat(tracker.findById(second.getId()).getName(), is("test2"));
    }
}

