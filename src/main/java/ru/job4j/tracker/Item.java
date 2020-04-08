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

    public static SortByNameAsc getAscComparator() {
        return new SortByNameAsc();
    }

    private static class SortByNameAsc implements Comparator<Item> {

        @Override
        public int compare(Item o1, Item o2) {
            return o1.name.compareTo(o2.name);
        }
    }

    public static SortByNameDesc getDescComparator() {
        return new SortByNameDesc();
    }

    private static class SortByNameDesc implements Comparator<Item> {

        @Override
        public int compare(Item o1, Item o2) {
            return o2.name.compareTo(o1.name);
        }
    }
}
