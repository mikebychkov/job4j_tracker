package ru.job4j.tracker.trackers;

import ru.job4j.tracker.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MemTracker implements Store {

    private final List<Item> items = new ArrayList<>();

    @Override
    public void init() {
    }

    public Item add(Item item) {
        item.setId(generateId());
        items.add(item);
        return item;
    }

    private int generateId() {
        Random rm = new Random();
        return (int) (rm.nextInt() + System.currentTimeMillis());
    }

    public List<Item> findAll() {
        return List.copyOf(this.items);
    }

    public List<Item> findByName(String key) {
        List<Item> resultItems = new ArrayList<>();
        for (Item it : items) {
            if (it.getName().equals(key)) {
                resultItems.add(it);
            }
        }
        return resultItems;
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? this.items.get(index) : null;
    }

    private int indexOf(int id) {
        int index = 0;
        for (Item it : this.items) {
            if (it.getId().equals(id)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        if (index != -1) {
            this.items.set(index, item);
            item.setId(id);
        }
        return index != -1;
    }

    public boolean delete(int id) {
        int index = indexOf(id);
        if (index != -1) {
            this.items.remove(index);
        }
        return index != -1;
    }

    @Override
    public void close() throws Exception {
    }
}
