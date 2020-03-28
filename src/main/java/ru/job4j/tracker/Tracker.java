package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

public class Tracker {
    /**
     * Массив для хранения заявок.
     */
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод добавления заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(generateId());
        items[position++] = item;
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    public Item[] findAll() {
        /*
        Item[] resultItems = new Item[this.position];
        int resultSize = 0;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i] != null) {
                resultItems[resultSize++] = this.items[i];
            }
        }
        return Arrays.copyOf(resultItems, resultSize);
        */
        return Arrays.copyOf(this.items, this.position);
    }

    public Item[] findByName(String key) {
        Item[] resultItems = new Item[this.position];
        int resultSize = 0;
        for (int i = 0; i < this.position; i++) {
            //if (this.items[i] != null && this.items[i].getName().equals(key)) {
            if (this.items[i].getName().equals(key)) {
                resultItems[resultSize++] = this.items[i];
            }
        }
        return Arrays.copyOf(resultItems, resultSize);
    }

    public Item findById(String id) {
        int index = indexOf(id);
        return index != -1 ? this.items[index] : null;
    }

    private int indexOf(String id) {
        for (int index = 0; index < position; index++) {
            //if (items[index] != null && items[index].getId().equals(id)) {
            if (items[index].getId().equals(id)) {
                return index;
            }
        }
        return -1;
    }

    public boolean replace(String id, Item item) {
        int index = indexOf(id);
        if (index != -1) {
            items[index] = item;
            items[index].setId(id);
        }
        return index != -1;
    }

    public boolean delete(String id) {
        int index = indexOf(id);
        if (index != -1) {
            this.items[index] = null;
            System.arraycopy(this.items, index + 1, this.items, index, this.position - index - 1);
            this.items[this.position - 1] = null;
            this.position--;
        }
        return index != -1;
    }
}
