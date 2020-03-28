package ru.job4j.tracker;

public class ShowAllAction implements UserAction {
    @Override
    public String name() {
        return "==== Show All Items ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        Item[] items = tracker.findAll();
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }
        return true;
    }
}
