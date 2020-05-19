package ru.job4j.tracker;

public class FindByIDAction implements UserAction {
    @Override
    public String name() {
        return "==== Find Item by ID ====";
    }

    @Override
    public boolean execute(Input input, MemTracker tracker) {
        String id = input.askStr("Enter ID:");
        Item res = tracker.findById(id);
        if (res != null) {
            System.out.print(res);
        } else {
            System.out.println("Wrong ID!");
        }
        return true;
    }
}
