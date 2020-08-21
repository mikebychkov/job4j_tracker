package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Store;

public class EditAction implements UserAction {
    @Override
    public String name() {
        return "==== Edit an Item ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        int id = Integer.parseInt(input.askStr("Enter ID:"));
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
