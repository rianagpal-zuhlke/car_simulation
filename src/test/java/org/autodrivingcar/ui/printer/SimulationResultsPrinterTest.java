package org.autodrivingcar.ui.printer;

import org.autodrivingcar.model.Car;
import org.autodrivingcar.model.Direction;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationResultsPrinterTest {
    SimulationResultsPrinter resultsPrinter = new SimulationResultsPrinter();

    @Test
    public void testPrintSimulationResultsWithCollision() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        Car car1 = new Car("TestCar1", 1, 2, Direction.N, "FF");
        Car car2 = new Car("TestCar2", 1, 2, Direction.S, "LR");
        Car car3 = new Car("TestCar3", 3, 4, Direction.E, "FFR");

        resultsPrinter.printSimulationResults(Arrays.asList(car1, car2, car3), car1, car2, 5);

        System.setOut(originalOut);

        String expectedOutput = """

                After simulation, the result is:
                - TestCar1, collides with TestCar2 at (1,2) at step 5
                - TestCar2, collides with TestCar1 at (1,2) at step 5
                - TestCar3, (3,4) E
                """;

        String actualOutput = outputStream.toString().strip().replaceAll("\\s+", " ");
        expectedOutput = expectedOutput.strip().replaceAll("\\s+", " ");

        assertEquals(expectedOutput, actualOutput, "The printed output for collision results should match the expected format.");
    }

    @Test
    public void testPrintSimulationResultsWithoutCollision() {
        SimulationResultsPrinter resultsPrinter = new SimulationResultsPrinter();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        Car car1 = new Car("TestCar1", 1, 2, Direction.N, "FF");
        Car car2 = new Car("TestCar2", 3, 4, Direction.S, "LR");
        Car car3 = new Car("TestCar3", 5, 6, Direction.E, "FFR");

        resultsPrinter.printSimulationResults(Arrays.asList(car1, car2, car3));

        System.setOut(originalOut);

        String expectedOutput = """

                After simulation, the result is:
                - TestCar1, (1,2) N
                - TestCar2, (3,4) S
                - TestCar3, (5,6) E
                """;

        String actualOutput = outputStream.toString().strip().replaceAll("\\s+", " ");
        expectedOutput = expectedOutput.strip().replaceAll("\\s+", " ");

        assertEquals(expectedOutput, actualOutput, "The printed output without collision results should match the expected format.");
    }
}

