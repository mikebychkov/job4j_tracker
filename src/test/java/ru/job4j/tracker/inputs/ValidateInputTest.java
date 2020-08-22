package ru.job4j.tracker.inputs;

import org.junit.Test;
import ru.job4j.tracker.inputs.Input;
import ru.job4j.tracker.inputs.StubInput;
import ru.job4j.tracker.inputs.ValidateInput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ValidateInputTest {

    @Test
    public void whenInvalidInput() {
        ByteArrayOutputStream mem = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(mem));
        //
        String[] data = {"one", "1"};
        Input stub = new StubInput(data);
        //
        ValidateInput input = new ValidateInput(stub);
        input.askInt("Enter");
        //
        assertThat(
                mem.toString(),
                is(String.format("Please enter validate data again.%n"))
        );
        System.setOut(out);
    }
}
