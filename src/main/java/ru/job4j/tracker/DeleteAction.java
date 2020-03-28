package ru.job4j.tracker;

public class DeleteAction implements UserAction {
    @Override
    public String name() {
        return "==== Delete an Item ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String id = input.askStr("Enter ID:");
        if (tracker.delete(id)) {
            System.out.println("Successfully deleted!");
        } else {
            System.out.println("Wrong ID!");
        }
        return true;
    }
}
