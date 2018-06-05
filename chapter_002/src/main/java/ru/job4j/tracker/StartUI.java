package ru.job4j.tracker;

/**
 * Class StartUI program start and menu.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class StartUI {

    /**
     * Constanta for menu - add new item.
     */
    private static final String ADD = "0";

    /**
     * Constanta for menu - show all items.
     */
    private static final String SHOW = "1";

    /**
     * Constanta for menu - edit item.
     */
    private static final String EDIT = "2";

    /**
     *  Constanta for menu - delete item.
     */
    private static final String DELETE = "3";

    /**
     *  Constanta for menu - find item by id.
     */
    private static final String ID = "4";

    /**
     * Constanta for menu - find item by name.
     */
    private static final String NAME = "5";

    /**
     * Constanta for menu - exit program.
     */
    private static final String EXIT = "6";

    /**
     * Input data from user.
     */
    private final Input input;

    /**
     * Storage of data.
     */
    private final Tracker tracker;

    /**
     * The initializer for the field.
     * @param input input data.
     * @param tracker Storage of data.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Main cycle of program.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Enter the menu item : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW.equals(answer)) {
                this.showAll();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (ID.equals(answer)) {
                this.findId();
            } else if (NAME.equals(answer)) {
                this.findName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * The method that implements the addition of a new task.
     */
    private void createItem() {
        System.out.println("------------ Add a new task --------------");
        String name = this.input.ask("Enter the name of the task :");
        String desc = this.input.ask("Enter the description of the task :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ New task with getId : " + item.getId() + " -----------");
    }

    /**
     * A method that implements the display of all tasks.
     */
    private void showAll() {
        System.out.println("------------ List of all tasks --------------");
        for (Item items : this.tracker.findAll()) {
            System.out.println("Id: " + items.getId() + " Name: "
                    + items.getName() + " Description: " + items.getDesc());
        }
        System.out.println("------------ End of list --------------");
    }

    /**
     * A method that implements the editing of an task by id.
     */
    private void editItem() {
        System.out.println("------------ Edit task --------------");
        String id = this.input.ask("Enter the task ID that you want to change :");
        String name = this.input.ask("Enter the name of the task :");
        String desc = this.input.ask("Enter the description of the task :");
        this.tracker.replace(id, new Item(id, name, desc));
        System.out.println("------------ Editing complete --------------");
        /*
        добавил в класс Item конструктор с id, при вызове метода replace обнуляется Id. правильным будет реализовать
        решение в методе класса трекер / выяснить требование -  оставлять старый id или генерировать новый
         */
    }

    /**
     * A method that implements the deleting of an task by id.
     */
    private void deleteItem() {
        System.out.println("------------ Delete task by id --------------");
        String id = this.input.ask("Enter the task ID that you want to delete :");
        boolean deleted = this.tracker.delete(id);
        if (deleted) {
            System.out.println("------------ Item was deleted --------------");
        } else {
            System.out.println("There is no tusks with id " + id);
        }
    }

    /**
     * A method that implements the finding of an task by id.
     */
    private void findId() {
        System.out.println("------------ Find task by id --------------");
        String id = this.input.ask("Enter the task ID that you want to find :");
        Item found = this.tracker.findById(id);
        if (this.tracker.findById(id) != null) {
            System.out.println("Id: " + found.getId() + " Name: "
                    + found.getName() + " Description: " + found.getDesc());
        } else {
            System.out.println("There is no task with this id.");
        }
        System.out.println("------------ End of search --------------");
    }

    /**
     * A method that implements the finding of an task by name.
     */
    private void findName() {
        System.out.println("------------ Find task by Name --------------");
        String name = this.input.ask("Enter the tasks name that you want to find :");
        Item[] items = this.tracker.findByName(name);
        if (items.length > 0) {
            for (Item item : items) {
                System.out.println("Id: " + item.getId() + " Name: "
                        + item.getName() + " Description: " + item.getDesc());
            }
        } else {
            System.out.println("There is no tusks with name " + name + ".");
        }
        System.out.println("------------ End of search --------------");
    }

    /**
     * Displays the menu.
     */
    private void showMenu() {
        System.out.println("Menu.");
        System.out.println("0. Add new Item.");
        System.out.println("1. Show all items.");
        System.out.println("2. Edit item.");
        System.out.println("3. Delete item.");
        System.out.println("4. Find item by Id.");
        System.out.println("5. Find items by name.");
        System.out.println("6. Exit Program.");
    }

    /**
     * Program start.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}