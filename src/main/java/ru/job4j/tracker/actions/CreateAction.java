package ru.job4j.tracker.actions;

import ru.job4j.tracker.inputs.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.trackers.Store;

public class CreateAction implements UserAction {

    @Override
    public String name() {
        return "==== Create a new Item ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        String name = input.askStr("Enter name:");
        Item item = new Item(name);
        tracker.add(item);
        return true;
    }
}
