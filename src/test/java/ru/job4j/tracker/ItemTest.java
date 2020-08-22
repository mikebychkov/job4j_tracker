package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.comparators.ItemByNameAsc;
import ru.job4j.tracker.comparators.ItemByNameDesc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void sort() {
        Item i1 = new Item("Cockroach");
        Item i2 = new Item("Wrestle");
        Item i3 = new Item("Anabel");

        List<Item> items = new ArrayList<Item>();
        items.add(i1);
        items.add(i2);
        items.add(i3);

        List<Item> exp = new ArrayList<Item>();
        exp.add(i3);
        exp.add(i1);
        exp.add(i2);

        Collections.sort(items);

        assertThat(items, is(exp));
    }

    @Test
    public void sortAsc() {
        Item i1 = new Item("Cockroach");
        Item i2 = new Item("Wrestle");
        Item i3 = new Item("Anabel");

        List<Item> items = new ArrayList<Item>();
        items.add(i1);
        items.add(i2);
        items.add(i3);

        List<Item> exp = new ArrayList<Item>();
        exp.add(i3);
        exp.add(i1);
        exp.add(i2);

        Collections.sort(items, new ItemByNameAsc());

        assertThat(items, is(exp));
    }

    @Test
    public void sortDesc() {
        Item i1 = new Item("Cockroach");
        Item i2 = new Item("Wrestle");
        Item i3 = new Item("Anabel");

        List<Item> items = new ArrayList<Item>();
        items.add(i1);
        items.add(i2);
        items.add(i3);

        List<Item> exp = new ArrayList<Item>();
        exp.add(i2);
        exp.add(i1);
        exp.add(i3);

        Collections.sort(items, new ItemByNameDesc());

        assertThat(items, is(exp));
    }
}
