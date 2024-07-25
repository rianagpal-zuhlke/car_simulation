package org.autodrivingcar.ui.printer;

import org.autodrivingcar.model.Car;

import org.autodrivingcar.utils.CarHelper;
import org.autodrivingcar.utils.StringFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationResultsPrinterTest {
    private ByteArrayOutputStream outputStream;
    private SimulationResultsPrinter simulationResultsPrinter;

    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        simulationResultsPrinter = new SimulationResultsPrinter(new PrintStream(outputStream));
    }

    @Test
    public void testPrintSimulationResultsWithCollision() {
        List<Car> cars = CarHelper.generateSampleCarList();

        simulationResultsPrinter.printSimulationResults(cars, cars.get(0), cars.get(1), 5);

        String expectedOutput = """

                After simulation, the result is:
                - TestCar1, collides with TestCar2 at (1,2) at step 5
                - TestCar2, collides with TestCar1 at (3,4) at step 5
                - TestCar3, (5,6) E
                """;

        String actualOutput = StringFormatter.normalizeLineSeparators(outputStream.toString().strip());
        expectedOutput = StringFormatter.normalizeLineSeparators(expectedOutput.strip());

        assertEquals(expectedOutput.strip(), actualOutput);
    }

    @Test
    public void testPrintSimulationResultsWithoutCollision() {
        simulationResultsPrinter.printSimulationResults(CarHelper.generateSampleCarList());

        String expectedOutput = """
                After simulation, the result is:
                - TestCar1, (1,2) N
                - TestCar2, (3,4) S
                - TestCar3, (5,6) E
                """;

        String actualOutput = StringFormatter.normalizeLineSeparators(outputStream.toString().strip());
        expectedOutput = StringFormatter.normalizeLineSeparators(expectedOutput.strip());

        assertEquals(expectedOutput.strip(), actualOutput);
    }

}

