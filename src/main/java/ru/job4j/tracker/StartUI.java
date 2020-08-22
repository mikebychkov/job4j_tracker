package ru.job4j.tracker;

import ru.job4j.tracker.actions.*;
import ru.job4j.tracker.inputs.ConsoleInput;
import ru.job4j.tracker.inputs.Input;
import ru.job4j.tracker.inputs.ValidateInput;
import ru.job4j.tracker.trackers.SqlTracker;
import ru.job4j.tracker.trackers.Store;

import java.util.List;

public class StartUI {

    public void init(Input input, Store tracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select:", actions.size());
            run = actions.get(select).execute(input, tracker);
        }
    }

    private void showMenu(List<UserAction> actions) {
        System.out.println();
        System.out.println("Menu:");
        int index = 0;
        for (UserAction ac : actions) {
            System.out.println(index++ + ". " + ac.name());
        }
    }

    public static List<UserAction> getActionsList() {
        return List.of(
                new CreateAction(),
                new ShowAllAction(),
                new FindByIDAction(),
                new FindByNameAction(),
                new ReplaceAction(),
                new DeleteAction(),
                new ExitAction()
        );
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Input validate = new ValidateInput(input);
        try (Store tracker = new SqlTracker()) {
            new StartUI().init(validate, tracker, getActionsList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Store tracker = new MemTracker();
        //new StartUI().init(validate, tracker, getActionsList());
    }
}
