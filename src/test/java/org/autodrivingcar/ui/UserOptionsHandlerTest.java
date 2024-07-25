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

import static org.junit.jupiter.api.Assertions.*;

public class UserOptionsHandlerTest {
    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;
    private SimulationManager testSimulationManager;

    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        testSimulationManager = new SimulationManager(printStream);
        testSimulationManager.clearCars();
    }

    @Test
    public void testStartConfiguresTheField() {
        String input = "10 20\n1\nTestCar\n5\n6\nN\nFFR\n2\n2\n";
        UserOptionsHandler userOptionsHandler = new UserOptionsHandler(
                testSimulationManager, generateScanner(input), new PrintStream(outputStream));

        userOptionsHandler.start();

        assertEquals(10, testSimulationManager.getFieldWidth());
        assertEquals(20, testSimulationManager.getFieldHeight());
    }

    @Test
    public void testHandleUserChoiceToAddCar() {
        String input = "10 20\n1\nTestCar\n1 2 N\nFFR\n2\n2\n";
        UserOptionsHandler userOptionsHandler = new UserOptionsHandler(
                testSimulationManager, generateScanner(input), new PrintStream(outputStream));

        userOptionsHandler.start();

        assertEquals(1, testSimulationManager.getCars().size());

        Car addedCar = testSimulationManager.getCars().getFirst();
        assertEquals("TestCar", addedCar.getCarName());
        assertEquals(1, addedCar.getX());
        assertEquals(Command.F, addedCar.getCommands()[0]);
        assertEquals(Command.R, addedCar.getCommands()[2]);
    }

    @Test
    public void testHandleUserChoiceToRunSimulation() {
        String input = "10 20\n1\nTestCar\n1\n2\nN\nFR\n2\n2\n";
        UserOptionsHandler userOptionsHandler = new UserOptionsHandler(
                testSimulationManager, generateScanner(input), new PrintStream(outputStream));

        userOptionsHandler.start();

        assertEquals(1, testSimulationManager.getCars().size());

        Car afterSimulation = testSimulationManager.getCars().getFirst();
        assertEquals("TestCar", afterSimulation.getCarName());
        assertEquals(1, afterSimulation.getX());
        assertEquals(3, afterSimulation.getY());
        assertEquals(Direction.E, afterSimulation.getDirection());

    }

    @Test
    public void testHandleInvalidUserChoice() {
        UserOptionsHandler userOptionsHandler = new UserOptionsHandler(testSimulationManager, generateScanner("\n"), printStream);
        userOptionsHandler.handleUserChoice(3);

        String expectedOutput = "Invalid option. Please try again.";
        assertEquals(expectedOutput, outputStream.toString().strip());
    }

    @Test
    public void testHandleUserChoiceToStartOver() {
        String input = "10 20\n1\nTestCar\n1 2 N\nFFR\n2\n1\n1\nTestCar2\n3 4 W\nRF\n2\n2\n";
        UserOptionsHandler userOptionsHandler = new UserOptionsHandler(
                testSimulationManager, generateScanner(input), new PrintStream(outputStream));

        userOptionsHandler.start();

        assertEquals(1, testSimulationManager.getCars().size());

        Car addedCar = testSimulationManager.getCars().getFirst();
        assertEquals("TestCar2", addedCar.getCarName());
        assertEquals(3, addedCar.getX());
        assertEquals(Command.R, addedCar.getCommands()[0]);
        assertEquals(Command.F, addedCar.getCommands()[1]);
    }

    @Test
    public void testHandleUserChoiceToExitApplication() {
        String input = "10 20\n1\nTestCar\n1\n2\nN\nFFR\n2\n2\n";
        UserOptionsHandler userOptionsHandler = new UserOptionsHandler(
                testSimulationManager, generateScanner(input), new PrintStream(outputStream));

        userOptionsHandler.start();

        String expectedOutput = "Thank you for running the simulation. Goodbye!";
        String actualOutput = outputStream.toString().strip();
        assertTrue(actualOutput.contains(expectedOutput));
    }

    @Test
    public void testHandleInvalidUserChoiceAfterSimulation() {
        String input = "\n5\n";
        UserOptionsHandler userOptionsHandler = new UserOptionsHandler(
                testSimulationManager, generateScanner(input), new PrintStream(outputStream));

        userOptionsHandler.handleUserChoiceAfterSimulation();

        String expectedOutput = "Invalid option. Please try again.";
        String actualOutput = outputStream.toString().strip();
        assertTrue(actualOutput.contains(expectedOutput));
    }

    private Scanner generateScanner(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        return new Scanner(inputStream);
    }

}
