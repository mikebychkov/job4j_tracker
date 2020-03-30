package ru.job4j.tracker;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ValidateInputTest {

    @Test
    public void whenInvalidInput() {
        ByteArrayOutputStream mem = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(mem));
        String[] data = {"one", "1"};
        ValidateInput input = new ValidateStubInput(data);
        input.askInt("Enter");
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("Enter")
                .add("Please enter validate data again.")
                .add("Enter")
                .toString();
        assertThat(
                mem.toString(),
                //is(String.format("Please enter validate data again.%n"))
                is(expect)
        );
        System.setOut(out);
    }
}
