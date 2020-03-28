package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void findAll() {
        Tracker tracker = new Tracker();

        Item item0 = new Item("test1");
        tracker.add(item0);

        Item item1 = new Item("test2");
        tracker.add(item1);

        Item[] expect = new Item[] {item0, item1};

        Item[] result = tracker.findAll();
        assertThat(result, is(expect));
    }

    @Test
    public void findByName() {
        Tracker tracker = new Tracker();

        Item item0 = new Item("test1");
        tracker.add(item0);

        Item item1 = new Item("test2");
        tracker.add(item1);

        Item[] expect = new Item[] {item1};

        Item[] result = tracker.findByName("test2");
        assertThat(result, is(expect));
    }

    @Test
    public void whenReplace() {
        Tracker tracker = new Tracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        String id = bug.getId();
        Item bugWithDesc = new Item("Bug with description");
        tracker.replace(id, bugWithDesc);
        assertThat(tracker.findById(id).getName(), is("Bug with description"));
    }

    @Test
    public void whenDelete() {
        Tracker tracker = new Tracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        String id = bug.getId();
        tracker.delete(id);
        assertThat(tracker.findById(id), is(nullValue()));
    }

    @Test
    public void whenDeleteWithShift() {
        Tracker tracker = new Tracker();

        Item bug1 = new Item("Bug 1");
        tracker.add(bug1);

        Item bug2 = new Item("Bug 2");
        tracker.add(bug2);

        Item bug3 = new Item("Bug 3");
        tracker.add(bug3);

        Item bug4 = new Item("Bug 4");
        tracker.add(bug4);

        String id = bug3.getId();
        tracker.delete(id);

        Item[] expect = new Item[] {bug1, bug2, bug4};

        assertThat(tracker.findAll(), is(expect));
    }
}
