package org.autodrivingcar.ui.printer;

import org.autodrivingcar.utils.StringFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserOptionsPrinterTest {
    private ByteArrayOutputStream outputStream;
    private UserOptionsPrinter userOptionsPrinter;

    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        userOptionsPrinter = new UserOptionsPrinter(new PrintStream(outputStream));
    }

    @Test
    public void testPrintingOfUserChoiceBeforeSimulation() {
        userOptionsPrinter.printUserChoiceBeforeSimulation();

        String expectedOutput = """
                Please choose from the following options:
                [1] Add a car to field
                [2] Run simulation
                """;


        String actualOutput = StringFormatter.normalizeLineSeparators(outputStream.toString().strip());
        expectedOutput = StringFormatter.normalizeLineSeparators(expectedOutput.strip());

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testPrintingOfUserChoiceAfterSimulation() {
        userOptionsPrinter.printUserChoiceAfterSimulation();

        String expectedOutput = """
                
                Please choose from the following options:
                [1] Start over
                [2] Exit
                """;
        String actualOutput = StringFormatter.normalizeLineSeparators(outputStream.toString().strip());
        expectedOutput = StringFormatter.normalizeLineSeparators(expectedOutput.strip());

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testPrintingOfInvalidChoiceMessage() {
        userOptionsPrinter.printInvalidChoiceMessage();

        String expectedOutput = "Invalid option. Please try again.";
        String actualOutput = StringFormatter.normalizeLineSeparators(outputStream.toString().strip());
        expectedOutput = StringFormatter.normalizeLineSeparators(expectedOutput.strip());

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testPrintingOfApplicationExitMessage() {
        userOptionsPrinter.printApplicationExitMessage();

        String expectedOutput = "Thank you for running the simulation. Goodbye!";
        String actualOutput = StringFormatter.normalizeLineSeparators(outputStream.toString().strip());
        expectedOutput = StringFormatter.normalizeLineSeparators(expectedOutput.strip());

        assertEquals(expectedOutput, actualOutput);
    }
}
