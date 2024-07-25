package org.autodrivingcar.ui;

import org.autodrivingcar.model.Command;
import org.autodrivingcar.simulation.SimulationManager;
import org.autodrivingcar.model.Car;
import org.autodrivingcar.model.Direction;
import org.autodrivingcar.utils.StringFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CarOptionsHandlerTest {
    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;
    private SimulationManager testSimulationManager;

    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        testSimulationManager = new SimulationManager(printStream);
    }

    @Test
    public void testConfigureField() {
        String input = "10 20\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        CarOptionsHandler carOptionsHandler = new CarOptionsHandler(scanner, printStream);

        carOptionsHandler.configureField(testSimulationManager);

        String expectedOutput = """
                Welcome to Auto Driving Car Simulation!
                Please enter the width and height of the simulation field in x y format:\s

                You have created a field of 10 x 20.
                """;
        expectedOutput = StringFormatter.normalizeLineSeparators(expectedOutput.strip());

        String actualOutput = StringFormatter.normalizeLineSeparators(outputStream.toString().strip());

        assertEquals(expectedOutput, actualOutput);

        assertEquals(10, testSimulationManager.getFieldWidth());
        assertEquals(20, testSimulationManager.getFieldHeight());
    }

    @Test
    public void testConfigureNewCar() {
        String input = "\nTestCar\n5\n6\nN\nFFR\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        CarOptionsHandler handler = new CarOptionsHandler(scanner, printStream);
        Car car = handler.configureNewCar();

        assertEquals("TestCar", car.getCarName());
        assertEquals(5, car.getX());
        assertEquals(6, car.getY());
        assertEquals(Direction.N, car.getDirection());
        assertArrayEquals(new Command[]{Command.F, Command.F, Command.R}, car.getCommands());

        String expectedOutput = """
                Please enter the name of the car:
                Please enter initial position of car TestCar in x y Direction format:
                Please enter the commands for car TestCar:
                """;
        expectedOutput = StringFormatter.normalizeLineSeparators(expectedOutput.strip());

        String actualOutput = StringFormatter.normalizeLineSeparators(outputStream.toString().strip());
        assertEquals(expectedOutput, actualOutput);
    }

}
