package ru.job4j.tracker;

public class ReplaceAction implements UserAction {
    @Override
    public String name() {
        return "==== Replace an Item ====";
    }

    @Override
    public boolean execute(Input input, MemTracker tracker) {
        String id = input.askStr("Enter ID:");
        Item newItem = new Item(
                input.askStr("Enter name:")
        );
        if (tracker.replace(id, newItem)) {
            System.out.println("Successfully replaced!");
        } else {
            System.out.println("Something went wrong!");
        }
        return true;
    }
}
