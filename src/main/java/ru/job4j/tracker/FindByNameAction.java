package ru.job4j.tracker;

import java.util.List;

public class FindByNameAction implements UserAction {
    @Override
    public String name() {
        return "==== Find Item by Name ====";
    }

    @Override
    public boolean execute(Input input, MemTracker tracker) {
        String name = input.askStr("Enter name:");
        List<Item> items = tracker.findByName(name);
        for (Item it : items) {
            System.out.println(it);
        }
        return true;
    }
}
