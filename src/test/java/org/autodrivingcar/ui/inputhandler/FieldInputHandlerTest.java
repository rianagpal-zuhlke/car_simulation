package org.autodrivingcar.ui.inputhandler;

import org.autodrivingcar.model.Field;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FieldInputHandlerTest {

    @Test
    public void testConfigureField() {
        String input = "100\n200\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        FieldInputHandler fieldInputHandler = new FieldInputHandler(scanner);

        Field field = fieldInputHandler.configureField();

        assertEquals(100, field.getWidth());
        assertEquals(200, field.getHeight());
    }
}
