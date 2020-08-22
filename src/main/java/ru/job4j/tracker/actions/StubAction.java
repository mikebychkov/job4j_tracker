package ru.job4j.tracker.actions;

import ru.job4j.tracker.inputs.Input;
import ru.job4j.tracker.trackers.Store;

public class StubAction implements UserAction {

    private boolean call = false;

    @Override
    public String name() {
        return "Stub action";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        this.call = true;
        return false;
    }

    public boolean isCall() {
        return call;
    }
}
