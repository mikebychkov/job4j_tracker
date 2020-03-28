package ru.job4j.tracker;

public class ReplaceAction implements UserAction {
    @Override
    public String name() {
        return "==== Replace an Item ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String id = input.askStr("Enter ID:");
        Item res = tracker.findById(id);
        if (res != null) {
            Item newItem = new Item(
                    input.askStr("Enter name:")
            );
            tracker.replace(id, newItem);
            System.out.println("Successfully replaced!");
        } else {
            System.out.println("Wrong ID!");
        }
        return true;
    }
}
