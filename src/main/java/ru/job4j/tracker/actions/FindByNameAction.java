package ru.job4j.tracker.actions;

import ru.job4j.tracker.inputs.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.trackers.Store;

import java.util.List;

public class FindByNameAction implements UserAction {
    @Override
    public String name() {
        return "==== Find Item by Name ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        String name = input.askStr("Enter name:");
        List<Item> items = tracker.findByName(name);
        for (Item it : items) {
            System.out.println(it);
        }
        return true;
    }
}
