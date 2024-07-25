package org.autodrivingcar.simulation;

import org.autodrivingcar.model.Car;
import org.autodrivingcar.model.Command;
import org.autodrivingcar.model.Direction;
import org.autodrivingcar.utils.StringFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimulationManagerTest {

    private ByteArrayOutputStream outputStream;
    private SimulationManager simulationManager;

    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        simulationManager = new SimulationManager(new PrintStream(outputStream));
    }

    @Test
    public void testAddCar() {
        Car car = new Car("TestCar", 0, 0, Direction.N, new Command[]{Command.F, Command.F});
        simulationManager.addCar(car);

        List<Car> cars = simulationManager.getCars();

        assertEquals(1, cars.size());
        assertEquals("TestCar", cars.getFirst().getCarName());
    }

    @Test
    public void testGetCars() {
        Car car1 = new Car("TestCar1", 0, 0, Direction.N, new Command[]{Command.F, Command.F});
        Car car2 = new Car("TestCar2", 1, 1, Direction.S, new Command[]{Command.F, Command.F});
        simulationManager.addCar(car1);
        simulationManager.addCar(car2);

        List<Car> cars = simulationManager.getCars();

        assertEquals(2, cars.size());
    }

    @Test
    public void testConfigureField() {
        simulationManager.configureField(10,20);

        assertEquals(10, simulationManager.getFieldWidth());
        assertEquals(20, simulationManager.getFieldHeight());
    }

    @Test
    public void testPrintingOfCarsList() {
        Car car1 = new Car("TestCar1", 0, 0, Direction.N, new Command[]{Command.F, Command.F});
        Car car2 = new Car("TestCar2", 1, 1, Direction.S, new Command[]{Command.F, Command.R});
        simulationManager.addCar(car1);
        simulationManager.addCar(car2);

        simulationManager.printCarsList();

        String expectedOutput = """
                Your current list of cars are:
                - TestCar1, (0,0) N, FF
                - TestCar2, (1,1) S, FR

                """;
        expectedOutput = StringFormatter.normalizeLineSeparators(expectedOutput.strip());
        String actualOutput = StringFormatter.normalizeLineSeparators(outputStream.toString().strip());

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testClearCars() {
        Car car = new Car("TestCar", 0, 0, Direction.N, new Command[]{Command.F, Command.F});
        simulationManager.addCar(car);
        simulationManager.clearCars();

        List<Car> cars = simulationManager.getCars();

        assertEquals(0, cars.size());
    }

    @Test
    public void testRunCarSimulation() {
        Car car1 = new Car("TestCar1", 0, 0, Direction.N, new Command[]{Command.F, Command.F});
        simulationManager.addCar(car1);

        simulationManager.runCarSimulation();

        String expectedOutput = "After simulation, the result is:";
        expectedOutput = StringFormatter.normalizeLineSeparators(expectedOutput.strip());
        String actualOutput = StringFormatter.normalizeLineSeparators(outputStream.toString().strip());

        assertTrue(actualOutput.contains(expectedOutput));
    }
}
