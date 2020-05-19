package ru.job4j.tracker;

public class EditAction implements UserAction {
    @Override
    public String name() {
        return "==== Edit an Item ====";
    }

    @Override
    public boolean execute(Input input, MemTracker tracker) {
        String id = input.askStr("Enter ID:");
        Item res = tracker.findById(id);
        if (res != null) {
            res.setName(
                    input.askStr("Enter name:")
            );
            System.out.println("Successfully edited!");
        } else {
            System.out.println("Wrong ID!");
        }
        return true;
    }
}
