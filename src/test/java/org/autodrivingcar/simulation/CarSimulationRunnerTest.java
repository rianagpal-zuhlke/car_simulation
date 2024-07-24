package org.autodrivingcar.simulation;

import org.autodrivingcar.model.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.autodrivingcar.model.Car;
import org.autodrivingcar.model.Direction;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarSimulationRunnerTest {
    private CarSimulationRunner simulationRunner;
    private CarManager carManager;
    private Field field;

    @BeforeEach
    void setUp() {
        simulationRunner = new CarSimulationRunner();
        carManager = new CarManager();
        field = new Field(10,10);
    }

    @Test
    void testRunCarSimulation_NoCollision() {

        Car car1 = new Car("TestCar1", 0, 0, Direction.N, "F");
        Car car2 = new Car("TestCar2", 1, 0, Direction.E, "F");

        carManager.addCar(car1);
        carManager.addCar(car2);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        simulationRunner.runCarSimulation(carManager, field);

        String output = outContent.toString();

        assertTrue(output.contains("TestCar1, (0,1) N"));
        assertTrue(output.contains("TestCar2, (2,0) E"));
    }

    @Test
    void testRunCarSimulation_WithCollision() {

        Car car1 = new Car("TestCar1", 1, 1, Direction.N, "F");
        Car car2 = new Car("TestCar2", 1, 3, Direction.S, "F");

        carManager.addCar(car1);
        carManager.addCar(car2);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        simulationRunner.runCarSimulation(carManager, field);

        String output = outContent.toString();

        assertTrue(output.contains("TestCar1, collides with TestCar2 at (1,2) at step 1"));
        assertTrue(output.contains("TestCar2, collides with TestCar1 at (1,2) at step 1"));
    }
}
