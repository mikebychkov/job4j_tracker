package ru.job4j.tracker.actions;

import ru.job4j.tracker.inputs.Input;
import ru.job4j.tracker.trackers.Store;

public interface UserAction {
    String name();
    boolean execute(Input input, Store tracker);
}
