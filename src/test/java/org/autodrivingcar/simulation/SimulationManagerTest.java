/*package org.autodrivingcar.simulation;

import org.autodrivingcar.model.Car;
import org.autodrivingcar.model.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarManagerTest {

    private SimulationManager carManager;

    @BeforeEach
    public void setUp() {
        carManager = new SimulationManager();
    }

    @Test
    public void testAddCar() {
        Car car = new Car("Car1", 0, 0, Direction.N, "LRM");
        carManager.addCar(car);

        List<Car> cars = carManager.getCars();

        assertEquals(1, cars.size());
        assertEquals("Car1", cars.getFirst().getCarName());
    }

    @Test
    public void testGetCars() {
        Car car1 = new Car("Car1", 0, 0, Direction.N, "LRM");
        Car car2 = new Car("Car2", 1, 1, Direction.S, "RML");
        carManager.addCar(car1);
        carManager.addCar(car2);

        List<Car> cars = carManager.getCars();

        assertEquals(2, cars.size());
        assertEquals("Car1", cars.get(0).getCarName());
        assertEquals("Car2", cars.get(1).getCarName());
    }

    @Test
    public void testPrintCarsList() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Car car = new Car("Car1", 0, 0, Direction.N, "LRM");
        carManager.addCar(car);

        carManager.printCarsList();

        String output = outContent.toString();

        assertTrue(output.contains("Your current list of cars are:"));
        assertTrue(output.contains("Car1"));
    }

    @Test
    public void testClearCars() {
        Car car = new Car("Car1", 0, 0, Direction.N, "LRM");
        carManager.addCar(car);
        carManager.clearCars();

        List<Car> cars = carManager.getCars();

        assertEquals(0, cars.size());
    }
}
*/