package ru.job4j.tracker;

import ru.job4j.tracker.actions.*;

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
        System.out.println("Menu:");
        int index = 0;
        for (UserAction ac : actions) {
            System.out.println(index++ + ". " + ac.name());
        }
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Input validate = new ValidateInput(input);
        Store tracker = new MemTracker();
        List<UserAction> actions = List.of(
                new CreateAction(),
                new ShowAllAction(),
                new FindByIDAction(),
                new FindByNameAction(),
                new ReplaceAction(),
                new DeleteAction(),
                new ExitAction()
        );
        new StartUI().init(validate, tracker, actions);
    }
    /*
    public static void main(String[] args) {
        Input validate = new ValidateInput(
                new ConsoleInput()
        );
        try (Store tracker = new SqlTracker()) {
            tracker.init();
            UserAction[] actions = {
                    new CreateAction()
            };
            new StartUI().init(validate, tracker, actions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
}
