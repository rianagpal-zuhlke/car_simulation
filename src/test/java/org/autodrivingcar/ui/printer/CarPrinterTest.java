package org.autodrivingcar.ui.printer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.autodrivingcar.utils.CarHelper;
import org.autodrivingcar.utils.StringFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarPrinterTest {
    private ByteArrayOutputStream outputStream;
    private CarPrinter carPrinter;

    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        carPrinter = new CarPrinter(new PrintStream(outputStream));
    }

    @Test
    public void testPrintingOfCarList() {
        carPrinter.printCarList(CarHelper.generateSampleCarList());
        String actualOutput = StringFormatter.normalizeLineSeparators(outputStream.toString().strip());

        String expectedOutput = """
                Your current list of cars are:
                - TestCar1, (1,2) N, FF
                - TestCar2, (3,4) S, LR
                - TestCar3, (5,6) E, FFR

                """;
        expectedOutput = StringFormatter.normalizeLineSeparators(expectedOutput.strip());

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testPrintingOfWelcomeMessage() {
        String expectedOutput = """
                Welcome to Auto Driving Car Simulation!
                Please enter the width and height of the simulation field in x y format:
                """;

        carPrinter.printWelcomeMessage();
        String actualOutput = StringFormatter.normalizeLineSeparators(outputStream.toString().strip());

        assertEquals(expectedOutput.strip(), actualOutput);
    }

    @Test
    public void testPrintingOfFieldConfiguration() {
        carPrinter.printFieldConfiguration(10, 20);

        String expectedOutput = """
                You have created a field of 10 x 20.
                """;

        String actualOutput = StringFormatter.normalizeLineSeparators(outputStream.toString().strip());

        assertEquals(expectedOutput.strip(), actualOutput);
    }

    @Test
    public void testPrintingOfInvalidFieldConfiguration() {
        carPrinter.printInvalidFieldConfiguration();

        String expectedOutput = """
                Width and height must be greater than zero. Please enter valid values:
                """;

        String actualOutput = StringFormatter.normalizeLineSeparators(outputStream.toString().strip());

        assertEquals(expectedOutput.strip(), actualOutput);
    }

    @Test
    public void testAskForCarName() {
        carPrinter.askForCarName();

        String expectedOutput = "Please enter the name of the car:";
        String actualOutput = StringFormatter.normalizeLineSeparators(outputStream.toString().strip());

        assertEquals(expectedOutput.strip(), actualOutput);
    }

    @Test
    public void testAskForCarPosition() {
        carPrinter.askForCarPosition("TestCar");

        String expectedOutput = "Please enter initial position of car TestCar in x y Direction format:";
        String actualOutput = StringFormatter.normalizeLineSeparators(outputStream.toString().strip());

        assertEquals(expectedOutput.strip(), actualOutput);
    }

    @Test
    public void testAskForCarCommands() {
        carPrinter.askForCarCommands("TestCar");

        String expectedOutput = "Please enter the commands for car TestCar:";

        String actualOutput = StringFormatter.normalizeLineSeparators(outputStream.toString().strip());

        assertEquals(expectedOutput.strip(), actualOutput);
    }

}
