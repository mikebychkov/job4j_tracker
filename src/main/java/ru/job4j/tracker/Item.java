package ru.job4j.tracker;

import java.util.*;

public class Item implements Comparable<Item> {
    private String id;
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name + "(" + this.id + ")";
    }

    @Override
    public int compareTo(Item item) {
        return this.name.compareTo(item.name);
    }
}
