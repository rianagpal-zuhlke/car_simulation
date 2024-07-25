package org.autodrivingcar.simulation;

import org.autodrivingcar.model.Command;
import org.autodrivingcar.utils.StringFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.autodrivingcar.model.Car;
import org.autodrivingcar.model.Direction;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarSimulationRunnerTest {
    private ByteArrayOutputStream outputStream;
    private CarSimulationRunner simulationRunner;
    private SimulationManager testSimulationManager;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        simulationRunner = new CarSimulationRunner(new PrintStream(outputStream));
        testSimulationManager = new SimulationManager(new PrintStream(outputStream));
    }

    @Test
    void testRunCarSimulation_NoCollision() {
        Car car1 = new Car("TestCar1", 0, 0, Direction.N, new Command[]{Command.F});
        Car car2 = new Car("TestCar2", 1, 0, Direction.E, new Command[]{Command.F});
        testSimulationManager.addCar(car1);
        testSimulationManager.addCar(car2);
        testSimulationManager.configureField(10,10);

        simulationRunner.runCarSimulation(testSimulationManager);

        String actualOutput = StringFormatter.normalizeLineSeparators(outputStream.toString().strip());
        assertTrue(actualOutput.contains("TestCar1, (0,1) N"));
        assertTrue(actualOutput.contains("TestCar2, (2,0) E"));
    }

    @Test
    void testRunCarSimulation_WithCollision() {

        Car car1 = new Car("TestCar1", 1, 1, Direction.N, new Command[]{Command.F});
        Car car2 = new Car("TestCar2", 1, 3, Direction.S, new Command[]{Command.F});

        testSimulationManager.addCar(car1);
        testSimulationManager.addCar(car2);
        testSimulationManager.configureField(10,10);

        simulationRunner.runCarSimulation(testSimulationManager);

        String actualOutput = StringFormatter.normalizeLineSeparators(outputStream.toString().strip());
        assertTrue(actualOutput.contains("TestCar1, collides with TestCar2 at (1,2) at step 1"));
        assertTrue(actualOutput.contains("TestCar2, collides with TestCar1 at (1,2) at step 1"));
    }
}
