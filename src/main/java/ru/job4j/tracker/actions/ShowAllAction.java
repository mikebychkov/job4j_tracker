package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Store;

import java.util.List;

public class ShowAllAction implements UserAction {
    @Override
    public String name() {
        return "==== Show All Items ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        List<Item> items = tracker.findAll();
        for (Item it : items) {
            System.out.println(it);
        }
        return true;
    }
}
