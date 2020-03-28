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
        return Integer.valueOf(this.sc.nextLine());
    }
}
