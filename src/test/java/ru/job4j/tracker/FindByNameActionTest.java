package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FindByNameActionTest {
    @Test
    public void findByName() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        //
        Tracker tracker = new Tracker();
        Item item = new Item("fix bug");
        tracker.add(item);
        //
        StubInput input = new StubInput(
                new String[] {"fix bug"}
        );
        //
        FindByNameAction act = new FindByNameAction();
        act.execute(input, tracker);
        //
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add(item.toString())
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        //
        System.setOut(def);
    }
}
