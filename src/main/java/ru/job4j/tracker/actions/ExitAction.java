package ru.job4j.tracker.actions;

import ru.job4j.tracker.inputs.Input;
import ru.job4j.tracker.trackers.Store;

public class ExitAction implements UserAction {
    @Override
    public String name() {
        return "==== Exit Program ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        return false;
    }
}
