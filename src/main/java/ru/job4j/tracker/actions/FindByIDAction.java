package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Store;

public class FindByIDAction implements UserAction {
    @Override
    public String name() {
        return "==== Find Item by ID ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        int id = Integer.parseInt(input.askStr("Enter ID:"));
        Item res = tracker.findById(id);
        if (res != null) {
            System.out.print(res);
        } else {
            System.out.println("Wrong ID!");
        }
        return true;
    }
}
