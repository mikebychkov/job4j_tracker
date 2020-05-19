package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Store;

public interface UserAction {
    String name();
    boolean execute(Input input, Store tracker);
}
