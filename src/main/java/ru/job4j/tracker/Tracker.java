package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Tracker {

    private final List<Item> items = new ArrayList<Item>();

    public Item add(Item item) {
        item.setId(generateId());
        items.add(item);
        return item;
    }

    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    public List<Item> findAll() {
        return List.copyOf(this.items);
    }

    public List<Item> findByName(String key) {
        List<Item> resultItems = new ArrayList<Item>();
        for (Item it : items) {
            if (it.getName().equals(key)) {
                resultItems.add(it);
            }
        }
        return resultItems;
    }

    public Item findById(String id) {
        int index = indexOf(id);
        return index != -1 ? this.items.get(index) : null;
    }

    private int indexOf(String id) {
        int index = 0;
        for (Item it : this.items) {
            if (it.getId().equals(id)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public boolean replace(String id, Item item) {
        int index = indexOf(id);
        if (index != -1) {
            this.items.set(index, item);
            item.setId(id);
        }
        return index != -1;
    }

    public boolean delete(String id) {
        int index = indexOf(id);
        if (index != -1) {
            this.items.remove(index);
        }
        return index != -1;
    }
}
