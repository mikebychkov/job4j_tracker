package ru.job4j.tracker.trackers;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.ConnectionRollback;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.trackers.SqlTracker;

import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;


public class SqlTrackerTest {

    Connection cn;

    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Before
    public void initialize() throws SQLException {
        cn = ConnectionRollback.create(this.init());
    }

    @Test
    public void createItem() {
        try (SqlTracker tracker = new SqlTracker(cn)) {
            tracker.add(new Item("name"));
            tracker.add(new Item("desc"));
            assertThat(tracker.findByName("desc").size(), is(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        try (SqlTracker tracker = new SqlTracker(cn)) {
            Item item = new Item("test1");
            tracker.add(item);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName(), is(item.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAll() {
        try (SqlTracker tracker = new SqlTracker(cn)) {
            Item item0 = new Item("test1");
            tracker.add(item0);

            Item item1 = new Item("test2");
            tracker.add(item1);

            List<Item> expect = List.of(item0, item1);
            List<Item> result = tracker.findAll();

            assertThat(result, is(expect));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findByName() {
        try (SqlTracker tracker = new SqlTracker(cn)) {
            Item item0 = new Item("test1");
            tracker.add(item0);

            Item item1 = new Item("test2");
            tracker.add(item1);

            List<Item> expect = List.of(item1);
            List<Item> result = tracker.findByName("test2");

            assertThat(result, is(expect));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenReplace() {
        try (SqlTracker tracker = new SqlTracker(cn)) {
            Item bug = new Item("Bug");
            tracker.add(bug);
            int id = bug.getId();
            Item bugWithDesc = new Item("Bug with description");
            tracker.replace(id, bugWithDesc);
            assertThat(tracker.findById(id).getName(), is("Bug with description"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenDelete() {
        try (SqlTracker tracker = new SqlTracker(cn)) {
            Item bug = new Item("Bug");
            tracker.add(bug);
            int id = bug.getId();
            tracker.delete(id);
            assertThat(tracker.findById(id), is(nullValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
