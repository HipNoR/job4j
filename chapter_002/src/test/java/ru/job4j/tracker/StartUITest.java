package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
    //создаем поле трекер
    private final Tracker tracker = new Tracker();
    // поле ссылки на стандартный вывод в консоль
    private final PrintStream stdout = System.out;
    // Поле - буфер для хранения данных вывода
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    // Метод возвращает Итем по порядковому индексу
    private final Item getItemByIndex(int index) {
        return this.tracker.findAll()[index];
    }
    // Строка Меню для проверки вывода в консоль
    private final StringBuilder menu = new StringBuilder()
            .append("------------------ Menu ------------------")
            .append(System.lineSeparator())
            .append("0. Add the new task.")
            .append(System.lineSeparator())
            .append("1. Show all task's.")
            .append(System.lineSeparator())
            .append("2. Edit task.")
            .append(System.lineSeparator())
            .append("3. Delete task.")
            .append(System.lineSeparator())
            .append("4. Find task by Id.")
            .append(System.lineSeparator())
            .append("5. Find tasks by Name.")
            .append(System.lineSeparator())
            .append("6. Exit Program.")
            .append(System.lineSeparator())
            .append("------------------ End ------------------")
            .append(System.lineSeparator());
    // Метод реализует замену стандартного вывода в консоль на вывод в память.
    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }
    //Метод реализует обратный выход в консоль
    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }
    // метод реализует иммитацию ввода пользователя и запуск программы
    public void inputAndStart(String[] input) {
        Input commands = new StubInput(input);
        new StartUI(commands, this.tracker).init();
    }
    // Метод добавляет в итемы в хранилище через цикл
    public void addTasks(int number) {
        for (int index = 0; index < number; index++) {
            this.tracker.add(new Item("test" + index, "desc" + index));
        }
    }


    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        inputAndStart(new String[]{"0", "test name", "desc", "0", "test name2", "desc2", "6"});
        assertThat(getItemByIndex(1).getName(), is("test name2"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        //Напрямую добавляем заявку
        Item item = this.tracker.add(new Item("test2", "desc2"));
        inputAndStart(new String[]{"2", item.getId(), "test name", "desc", "6"});
        assertThat(this.tracker.findById(item.getId()).getName(), is("test name"));
    }

    @Test
    public void whenDeleteOneOfThreeThenTrackerContainsTwo() {
        // Добавляем заявки
        addTasks(3);
        // Сохраняем id удаляемого элемента
        String target = getItemByIndex(1).getId();
        inputAndStart(new String[] {"3", target, "6"});
        // Проверяем весь выходной массив - действительно ли удаленный элемент отсутсутствует.
        boolean task = true;
        for (Item item : this.tracker.findAll()) {
            if (item.getId().equals(target)) {
                task = false;
            }
        }
        // Проверяем ожидаемый результат
        assertThat(task, is(true));
    }

    @Test
    public void whenTrackerContainsOneShowAllShowOneAndMenu() {
        Item first = this.tracker.add(new Item("test0", "desc0"));
        inputAndStart(new String[] {"1", "6"});
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(menu)
                                .append("------------ List of all tasks --------------")
                                .append(System.lineSeparator())
                                .append("Id: " + first.getId() + " Name: "
                                        + first.getName() + " Description: " + first.getDesc())
                                .append(System.lineSeparator())
                                .append("------------ End of list --------------")
                                .append(System.lineSeparator())
                                .append(menu)
                                .append("Exit program.")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    @Test
    public void whenTrackerContainsThreeFindByIdShowOneAndMenu() {
        // Добавляем заявки
        addTasks(3);
        inputAndStart(new String[] {"4", getItemByIndex(1).getId(), "6"});
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(menu)
                                .append("------------ Find task by id --------------")
                                .append(System.lineSeparator())
                                .append("Id: " + getItemByIndex(1).getId()
                                        + " Name: " + getItemByIndex(1).getName()
                                        + " Description: " + getItemByIndex(1).getDesc())
                                .append(System.lineSeparator())
                                .append("------------ End of search --------------")
                                .append(System.lineSeparator())
                                .append(menu)
                                .append("Exit program.")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    @Test
    public void whenTrackerContainsThreeFindByIdCantFind() {
        addTasks(3);
        inputAndStart(new String[] {"4", "123", "6"});
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(menu)
                                .append("------------ Find task by id --------------")
                                .append(System.lineSeparator())
                                .append("There is no task with this id.")
                                .append(System.lineSeparator())
                                .append("------------ End of search --------------")
                                .append(System.lineSeparator())
                                .append(menu)
                                .append("Exit program.")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    @Test
    public void whenTrackerContainsThreeFindByNameShowTwoAndMenu() {
        addTasks(3);
        Item third = this.tracker.add(new Item("test2", "desc2"));
        inputAndStart(new String[] {"5", "test2", "6"});
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(menu)
                                .append("------------ Find task by Name --------------")
                                .append(System.lineSeparator())
                                .append("Id: " + getItemByIndex(2).getId()
                                        + " Name: " + getItemByIndex(2).getName()
                                        + " Description: " + getItemByIndex(2).getDesc())
                                .append(System.lineSeparator())
                                .append("Id: " + third.getId() + " Name: "
                                        + third.getName() + " Description: " + third.getDesc())
                                .append(System.lineSeparator())
                                .append("------------ End of search --------------")
                                .append(System.lineSeparator())
                                .append(menu)
                                .append("Exit program.")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    @Test
    public void whenTrackerContainsThreeFindByNameCantFindAndMenu() {
        addTasks(3);
        inputAndStart(new String[] {"5", "test3", "6"});
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(menu)
                                .append("------------ Find task by Name --------------")
                                .append(System.lineSeparator())
                                .append("There is no tasks with name test3.")
                                .append(System.lineSeparator())
                                .append("------------ End of search --------------")
                                .append(System.lineSeparator())
                                .append(menu)
                                .append("Exit program.")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }
}