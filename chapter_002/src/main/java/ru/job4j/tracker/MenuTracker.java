package ru.job4j.tracker;

import java.util.List;

/**
 * A class that implements the editing of an task by id.
 */
class EditItem extends BaseAction {

    public EditItem() {
        super(2, "Edit task.");
    }

    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Edit task --------------");
        String id = input.ask("Enter the task ID that you want to change :");
        String name = input.ask("Enter the name of the task :");
        String desc = input.ask("Enter the description of the task :");
        tracker.replace(id, new Item(name, desc));
        System.out.println("------------ Editing complete --------------");
    }
}

/**
 * A class that implements the deleting of an task by id.
 */
class DeleteItem extends BaseAction {

    public DeleteItem() {
        super(3, "Delete task.");
    }

    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Delete task by id --------------");
        String id = input.ask("Enter the task ID that you want to delete :");
        boolean deleted = tracker.delete(id);
        if (deleted) {
            System.out.println("------------ Item was deleted --------------");
        } else {
            System.out.println(String.format("There is no tusks with id %s", id));
        }
    }
}

/**
 * A class that implements the finding of an task by id.
 */
class FindItemById extends BaseAction {

    public FindItemById() {
        super(4, "Find task by Id.");
    }

    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Find task by id --------------");
        String id = input.ask("Enter the task ID that you want to find :");
        Item found = tracker.findById(id);
        if (found != null) {
            System.out.println(String.format("Id: %s Name: %s Description: %s",
                    found.getId(), found.getName(), found.getDesc()));
        } else {
            System.out.println("There is no task with this id.");
        }
        System.out.println("------------ End of search --------------");
    }
}

/**
 * A class that implements the finding of an task by name.
 */
class FindItemByName extends BaseAction {

    public FindItemByName() {
        super(5, "Find tasks by Name.");
    }

    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Find task by Name --------------");
        String name = input.ask("Enter the tasks name that you want to find :");
        List<Item> items = tracker.findByName(name);
        if (items.size() > 0) {
            for (Item item : items) {
                System.out.println(String.format("Id: %s Name: %s Description: %s",
                        item.getId(), item.getName(), item.getDesc()));
            }
        } else {
            System.out.println(String.format("There is no tasks with name %s.", name));
        }
        System.out.println("------------ End of search --------------");
    }
}

class Exit extends BaseAction {

    public Exit() {
        super(6, "Exit Program.");
    }

    public void execute(Input input, Tracker tracker) {

    }
}

/**
 * MenuTracker class that implements menu actions.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class MenuTracker {

    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[7];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        this.actions[0] = this.new AddItem();
        this.actions[1] = new MenuTracker.ShowAll();
        this.actions[2] = new EditItem();
        this.actions[3] = new DeleteItem();
        this.actions[4] = new FindItemById();
        this.actions[5] = new FindItemByName();
        this.actions[6] = new Exit();
    }

    public int[] getRange() {
        int[] range = new int[this.actions.length];
        for (int index = 0; index < range.length; index++) {
            range[index] = index;
        }
        return range;
    }

    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    public void show() {
        System.out.println("------------------ Menu ------------------");
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
        System.out.println("------------------ End ------------------");
    }

    /**
     * The class that implements the addition of a new task.
     */
    private class AddItem extends BaseAction {

        public AddItem() {
            super(0, "Add the new task.");
        }

        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Enter the name of the task :");
            String desc = input.ask("Enter the description of the task :");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------  A new task with id: " + item.getId() + " -----------");
        }
    }

    /**
     * A class that implements the mapping of all tasks.
     */
    private static class ShowAll extends BaseAction {

        public ShowAll() {
            super(1, "Show all task's.");
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ List of all tasks --------------");
            for (Item items : tracker.findAll()) {
                System.out.println(
                        String.format("Id: %s Name: %s Description: %s",
                                items.getId(), items.getName(), items.getDesc()));
            }
            System.out.println("------------ End of list --------------");
        }
    }
}
