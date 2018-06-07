package ru.job4j.tracker;

/**
 * A class that implements the editing of an task by id.
 */
class EditItem implements UserAction {
    public int key() {
        return 2;
    }

    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Edit task --------------");
        String id = input.ask("Enter the task ID that you want to change :");
        String name = input.ask("Enter the name of the task :");
        String desc = input.ask("Enter the description of the task :");
        tracker.replace(id, new Item(name, desc));
        System.out.println("------------ Editing complete --------------");
    }

    public String info() {
        return String.format("%s. %s", this.key(), "Edit task.");
    }
}

/**
 * A class that implements the deleting of an task by id.
 */
class DeleteItem implements UserAction {
    public int key() {
        return 3;
    }

    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Delete task by id --------------");
        String id = input.ask("Enter the task ID that you want to delete :");
        boolean deleted = tracker.delete(id);
        if (deleted) {
            System.out.println("------------ Item was deleted --------------");
        } else {
            System.out.println("There is no tusks with id " + id);
        }
    }

    public String info() {
        return String.format("%s. %s", this.key(), "Delete task.");
    }
}

/**
 * A class that implements the finding of an task by id.
 */
class FindItemById implements UserAction {
    public int key() {
        return 4;
    }

    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Find task by id --------------");
        String id = input.ask("Enter the task ID that you want to find :");
        Item found = tracker.findById(id);
        if (tracker.findById(id) != null) {
            System.out.println("Id: " + found.getId() + " Name: "
                    + found.getName() + " Description: " + found.getDesc());
        } else {
            System.out.println("There is no task with this id.");
        }
        System.out.println("------------ End of search --------------");
    }

    public String info() {
        return String.format("%s. %s", this.key(), "Find task by Id.");
    }
}

/**
 * A class that implements the finding of an task by name.
 */
class FindItemByName implements UserAction {
    public int key() {
        return 5;
    }

    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Find task by Name --------------");
        String name = input.ask("Enter the tasks name that you want to find :");
        Item[] items = tracker.findByName(name);
        if (items.length > 0) {
            for (Item item : items) {
                System.out.println("Id: " + item.getId() + " Name: "
                        + item.getName() + " Description: " + item.getDesc());
            }
        } else {
            System.out.println("There is no tasks with name " + name + ".");
        }
        System.out.println("------------ End of search --------------");
    }

    public String info() {
        return String.format("%s. %s", this.key(), "Find tasks by Name.");
    }
}

class Exit implements UserAction {
    public int key() {
        return 6;
    }

    public void execute(Input input, Tracker tracker) {

    }

    public String info() {
        return String.format("%s. %s", this.key(), "Exit Program.");
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
    private class AddItem implements UserAction {
        public int key() {
            return 0;
        }

        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Enter the name of the task :");
            String desc = input.ask("Enter the description of the task :");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------  A new task with id: " + item.getId() + " -----------");
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Add the new task.");
        }
    }

    /**
     * A class that implements the mapping of all tasks.
     */
    private static class ShowAll implements UserAction {
        public int key() {
            return 1;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ List of all tasks --------------");
            for (Item items : tracker.findAll()) {
                System.out.println(
                        String.format("%s: %s %s: %s %s: %s", "Id", items.getId(),
                                "Name", items.getName(), "Description", items.getDesc()));
            }
            System.out.println("------------ End of list --------------");
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Show all task's.");
        }
    }

}
