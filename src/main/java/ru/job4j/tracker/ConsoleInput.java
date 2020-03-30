package ru.job4j.tracker;

import java.util.Scanner;

public class ConsoleInput implements Input {

    private Scanner sc = new Scanner(System.in);

    @Override
    public String askStr(String question) {
        System.out.println(question);
        return this.sc.nextLine();
    }

    @Override
    public int askInt(String question) {
        System.out.println(question);
        return Integer.valueOf(askStr(question));
    }

    @Override
    public int askInt(String question, int max) {
        int select = askInt(question);
        if (select >= 0 && select < max) {
            return select;
        } else {
            throw new IllegalStateException(String.format("Out of about %s > [0, %s]", select, max));
        }
    }

    public static void main(String[] args) {
        ConsoleInput ci = new ConsoleInput();
        ci.askInt("Enter:");
    }
}
