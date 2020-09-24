package ru.job4j.tracker.trackers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import ru.job4j.tracker.Item;

import static org.junit.Assert.assertEquals;

public class HbmTrackerTest {

    private static final Logger LOGGER = LogManager.getLogger(HbmTrackerTest.class);

    @Test
    public void addThenFind() throws Exception {
        HbmTracker tracker = new HbmTracker();

        Item item = new Item("Hibernate");

        tracker.add(item);

        Item rsl = tracker.findAll().get(0);

        assertEquals(item.getName(), rsl.getName());

        tracker.close();
    }

    @Test
    public void addThenFindByName() throws Exception {
        HbmTracker tracker = new HbmTracker();

        Item item1 = new Item("Hibernate");
        Item item2 = new Item("Hibernate");

        tracker.add(item1);
        tracker.add(item2);

        int rsl = tracker.findByName("Hibernate").size();

        assertEquals(2, rsl);

        tracker.close();
    }

    @Test
    public void addThenDelete() throws Exception {
        HbmTracker tracker = new HbmTracker();

        Item item = new Item("Hibernate");

        tracker.add(item);

        Item rslItem = tracker.findAll().get(0);

        tracker.delete(rslItem.getId());

        int rsl = tracker.findAll().size();

        assertEquals(0, rsl);

        tracker.close();
    }

    @Test
    public void addThenFindById() throws Exception {
        HbmTracker tracker = new HbmTracker();

        Item item1 = new Item("Hibernate#1");
        Item item2 = new Item("Hibernate#2");

        item1 = tracker.add(item1);
        item2 = tracker.add(item2);

        Item rsl = tracker.findById(item2.getId());

        assertEquals("Hibernate#2", rsl.getName());

        tracker.close();
    }

    @Test
    public void addThenReplace() throws Exception {
        HbmTracker tracker = new HbmTracker();

        Item item1 = new Item("Hibernate#1");
        Item item2 = new Item("Hibernate#2");

        item1 = tracker.add(item1);
        item2 = tracker.add(item2);

        tracker.replace(item2.getId(), item1);

        Item rsl = tracker.findById(item2.getId());

        assertEquals("Hibernate#1", rsl.getName());

        tracker.close();
    }
}
