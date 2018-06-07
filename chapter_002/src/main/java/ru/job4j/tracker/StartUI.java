package ru.job4j.tracker;

/**
 * Class StartUI program start and menu.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class StartUI {

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
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        int key;
        do {
            menu.show();
            key = Integer.valueOf(this.input.ask("Select number of menu:"));
            if (key >= 0 && key < 6) {
                menu.select(key);
            } else if (key == 6) {
                System.out.println("Exit program.");
            } else {
                System.out.println("Wrong number.");
            }
        } while (key != 6);
    }

    /**
     * Program start.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}