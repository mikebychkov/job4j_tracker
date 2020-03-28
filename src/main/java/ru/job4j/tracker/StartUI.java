package ru.job4j.tracker;

//import java.util.Scanner;

public class StartUI {

    public static void createItem(Input scanner, Tracker tracker) {
        System.out.println("==== Create a new Item ====");
        String name = scanner.askStr("Enter name:");
        Item item = new Item(name);
        tracker.add(item);
    }

    public static void showAllItems(Input scanner, Tracker tracker) {
        System.out.println("==== Show All Items ====");
        Item[] items = tracker.findAll();
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }
    }

    public static void editItem(Input scanner, Tracker tracker) {
        System.out.println("==== Edit an Item ====");
        String id = scanner.askStr("Enter ID:");
        Item res = tracker.findById(id);
        if (res != null) {
            res.setName(
                    scanner.askStr("Enter name:")
            );
            System.out.println("Successfully edited!");
        } else {
            System.out.println("Wrong ID!");
        }
    }

    public static void replaceItem(Input scanner, Tracker tracker) {
        System.out.println("==== Replace an Item ====");
        String id = scanner.askStr("Enter ID:");
        Item res = tracker.findById(id);
        if (res != null) {
            Item newItem = new Item(
                    scanner.askStr("Enter name:")
            );
            tracker.replace(id, newItem);
            System.out.println("Successfully replaced!");
        } else {
            System.out.println("Wrong ID!");
        }
    }

    public static void deleteItem(Input scanner, Tracker tracker) {
        System.out.println("==== Delete an Item ====");
        String id = scanner.askStr("Enter ID:");
        if (tracker.delete(id)) {
            System.out.println("Successfully deleted!");
        } else {
            System.out.println("Wrong ID!");
        }
    }

    public static void findItemByID(Input scanner, Tracker tracker) {
        System.out.println("==== Find Item by ID ====");
        String id = scanner.askStr("Enter ID:");
        Item res = tracker.findById(id);
        if (res != null) {
            System.out.print(res);
        } else {
            System.out.println("Wrong ID!");
        }
    }

    public static void findItemByName(Input scanner, Tracker tracker) {
        System.out.println("==== Find Item by Name ====");
        String name = scanner.askStr("Enter name:");
        Item[] items = tracker.findByName(name);
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }
    }

    public void init(Input scanner, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            int select = scanner.askInt("Select:");
            if (select == 0) {
                StartUI.createItem(scanner, tracker);
            } else if (select == 1) {
                StartUI.showAllItems(scanner, tracker);
            } else if (select == 2) {
                StartUI.editItem(scanner, tracker);
            } else if (select == 3) {
                StartUI.deleteItem(scanner, tracker);
            } else if (select == 4) {
                StartUI.findItemByID(scanner, tracker);
            } else if (select == 5) {
                StartUI.findItemByName(scanner, tracker);
            } else if (select == 6) {
                StartUI.replaceItem(scanner, tracker);
            } else if (select == 7) {
                run = false;
            }
        }
    }

    private void showMenu() {
        System.out.println("======================");
        System.out.println("Menu:");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Replace item");
        System.out.println("7. Exit Program");
    }

    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        Input scanner = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(scanner, tracker);
    }
}
