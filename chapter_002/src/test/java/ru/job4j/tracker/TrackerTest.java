package ru.job4j.tracker;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {
//    @Test
//    public void whenAddNewItemThenTrackerHasSameItem() {
//        Tracker tracker = new Tracker();
//        Item item = new Item("test1", "testDescription");
//        tracker.add(item);
//        assertThat(tracker.findByName("test1").get(0).getName(), is(item.getName()));
//    }
//
//    @Test
//    public void whenReplaceNameThenReturnNewName() {
//        Tracker tracker = new Tracker();
//        Item previous = new Item("test1", "testDescription");
//        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
//        tracker.add(previous);
//        // Создаем новую заявку.
//        Item next = new Item("test2", "testDescription2");
//        // Проставляем старый id из previous, который был сгенерирован выше.
//        next.setId(previous.getId());
//        // Обновляем заявку в трекере.
//        tracker.replace(previous.getId(), next);
//        // Проверяем, что заявка с таким id имеет новые имя test2.
//        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
//    }
//
//    @Test
//    public void whenDeleteThenReturnNewItem() {
//        Tracker tracker = new Tracker();
//        // Создаем три заявки и добавляем их все в трекер. Каждая получает уникальный id.
//        Item first = new Item("test1", "testDescription1");
//        tracker.add(first);
//        Item second = new Item("test2", "testDescription2");
//        tracker.add(second);
//        Item third = new Item("test3", "testDescription3");
//        tracker.add(third);
//        // Удаляем второй объект Item по его id.
//        tracker.delete(second.getId());
//        // Создаем ожидаемый массив класса Item.
//        List<Item> expected = new ArrayList<>();
//        expected.add(first);
//        expected.add(third);
//        // Проверяем, что итоговый и ожидаемый массив равны.
//        assertThat(tracker.findAll(), is(expected));
//
//    }
//
//    @Test
//    public void whenDeleteAndCantFindThenReturnNewItem() {
//        Tracker tracker = new Tracker();
//        // Создаем три заявки и добавляем их все в трекер. Каждая получает уникальный id.
//        Item first = new Item("test1", "testDescription1");
//        tracker.add(first);
//        Item second = new Item("test2", "testDescription2");
//        tracker.add(second);
//        Item third = new Item("test3", "testDescription3");
//        tracker.add(third);
//        // Удаляем второй объект Item по его id.
//        tracker.delete(third.getId());
//        tracker.delete(third.getId());
//        tracker.delete(third.getId());
//        // Создаем ожидаемый массив класса Item.
//        List<Item> expected = new ArrayList<>();
//        expected.add(first);
//        expected.add(second);
//        // Проверяем, что итоговый и ожидаемый массив равны.
//        assertThat(tracker.findAll(), is(expected));
//
//    }
//
//    @Test
//    public void whenFindAllThenReturnAll() {
//        Tracker tracker = new Tracker();
//        // Создаем три заявки и добавляем их все в трекер. Каждая получает уникальный id.
//        Item first = new Item("test1", "testDescription1");
//        tracker.add(first);
//        Item second = new Item("test2", "testDescription2");
//        tracker.add(second);
//        Item third = new Item("test3", "testDescription3");
//        tracker.add(third);
//        // Создаем ожидаемый массив класса Item.
//        List<Item> expected = new ArrayList<>();
//        expected.add(first);
//        expected.add(second);
//        expected.add(third);
//        // Проверяем, что массив, передаваемый через метод findAll и ожидаемый массив равны.
//        assertThat(tracker.findAll(), is(expected));
//    }
//
//    @Test
//    public void whenFindByNameThenReturnByName() {
//        Tracker tracker = new Tracker();
//        // Создаем три заявки и добавляем их все в трекер. Каждая получает уникальный id, две имеют одинаковое имя.
//        Item first = new Item("test1", "testDescription1");
//        tracker.add(first);
//        Item second = new Item("test2", "testDescription2");
//        tracker.add(second);
//        Item third = new Item("test2", "testDescription3");
//        tracker.add(third);
//        // Создаем ожидаемый массив класса Item из двух элементов с одинаковым именем.
//        List<Item> expected = new ArrayList<>();
//        expected.add(second);
//        expected.add(third);
//        // Проверяем, что массив, передаваемый через метод findAll и ожидаемый массив равны.
//        assertThat(tracker.findByName("test2"), is(expected));
//    }
//
//    @Test
//    public void whenFindByIdThenReturnItem() {
//        Tracker tracker = new Tracker();
//        // Создаем три заявки и добавляем их все в трекер. Каждая получает уникальный id.
//        Item first = new Item("test1", "testDescription1");
//        tracker.add(first);
//        Item second = new Item("test2", "testDescription2");
//        tracker.add(second);
//        Item third = new Item("test3", "testDescription3");
//        tracker.add(third);
//        // Создаем объект класса Item и присваеваем ему id одного из элементов добавленных в трекер.
//        Item expected = new Item("test2", "testDescription2");
//        expected.setId(second.getId());
//        // Сравниваем, что имя возвращенного элемента методом findById равно имени ожидаемого элемента.
//        assertThat(tracker.findById(second.getId()).getName(), is("test2"));
//    }
//    @Test
//    public void whenFindByIdAndCantFindThenReturnNull() {
//        Tracker tracker = new Tracker();
//        // Создаем три заявки и добавляем их все в трекер. Каждая получает уникальный id.
//        Item first = new Item("test1", "testDescription1");
//        tracker.add(first);
//        Item second = new Item("test2", "testDescription2");
//        tracker.add(second);
//        Item third = new Item("test3", "testDescription3");
//        tracker.add(third);
//        // Создаем объект класса Item и инициализируем его null.
//        Item expected = null;
//        // Сравниваем, что если поиск не нашел элемента, то метод возвращает null.
//        assertThat(tracker.findById("forTest"), is(expected));
//    }
//
//
//    @Test
//    public void justTest() {
//        Tracker tracker = new Tracker();
//        tracker.add(new Item("sobaka bla", "first task"));
//    }
//
//    @Test
//    public void secondTest() {
//        Tracker tracker = new Tracker();
//        List<Item> list = tracker.findAll();
//        for (Item item: list) {
//            System.out.println(String.format("Id: %s Name: %s Description: %s",
//                    item.getId(), item.getName(), item.getDesc()));
//        }
//    }
//
//    @Test
//    public void thirdTest() {
//        Tracker tracker = new Tracker();
//        List<Item> list = tracker.findByName("first");
//        for (Item item: list) {
//            System.out.println(String.format("Id: %s Name: %s Description: %s Time: %s",
//                    item.getId(), item.getName(), item.getDesc(), item.getCreated()));
//        }
//    }
//
//    @Test
//    public void fourthTest() {
//        Tracker tracker = new Tracker();
//        Item item = tracker.findById("4");
//        System.out.println(String.format("Id: %s Name: %s Description: %s",
//                item.getId(), item.getName(), item.getDesc()));
//    }
}

