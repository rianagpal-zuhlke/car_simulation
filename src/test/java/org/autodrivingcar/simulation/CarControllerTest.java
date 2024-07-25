package org.autodrivingcar.simulation;

import org.autodrivingcar.model.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.autodrivingcar.model.Car;
import org.autodrivingcar.model.Direction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarControllerTest {
    private CarController carController;

    @BeforeEach
    void setUp() {
        carController = new CarController(10, 10);
    }

    @Test
    void testRotateLeftFromNorth() {
        Car car = new Car("TestCar", 1, 4, Direction.N, new Command[]{Command.L});
        carController.executeCarCommand(car, Command.L);
        assertEquals(Direction.W, car.getDirection());
    }

    @Test
    void testRotateLeftFromEast() {
        Car car = new Car("TestCar", 1, 4, Direction.E, new Command[]{Command.L});
        carController.executeCarCommand(car, Command.L);
        assertEquals(Direction.N, car.getDirection());
    }

    @Test
    void testRotateLeftFromSouth() {
        Car car = new Car("TestCar", 1, 4, Direction.S, new Command[]{Command.L});
        carController.executeCarCommand(car, Command.L);
        assertEquals(Direction.E, car.getDirection());
    }

    @Test
    void testRotateLeftFromWest() {
        Car car = new Car("TestCar", 1, 4, Direction.W, new Command[]{Command.L});
        carController.executeCarCommand(car, Command.L);
        assertEquals(Direction.S, car.getDirection());
    }

    @Test
    void testRotateRightFromNorth() {
        Car car = new Car("TestCar", 1, 4, Direction.N, new Command[]{Command.R});
        carController.executeCarCommand(car, Command.R);
        assertEquals(Direction.E, car.getDirection());
    }

    @Test
    void testRotateRightFromSouth() {
        Car car = new Car("TestCar", 1, 4, Direction.S, new Command[]{Command.R});
        carController.executeCarCommand(car, Command.R);
        assertEquals(Direction.W, car.getDirection());
    }

    @Test
    void testRotateRightFromEast() {
        Car car = new Car("TestCar", 1, 4, Direction.E, new Command[]{Command.R});
        carController.executeCarCommand(car, Command.R);
        assertEquals(Direction.S, car.getDirection());
    }

    @Test
    void testRotateRightFromWest() {
        Car car = new Car("TestCar", 1, 4, Direction.W, new Command[]{Command.R});
        carController.executeCarCommand(car, Command.R);
        assertEquals(Direction.N, car.getDirection());
    }

    @Test
    void testMoveForwardFromNorth() {
        Car car = new Car("TestCar", 1, 4, Direction.N, new Command[]{Command.F});
        carController.executeCarCommand(car, Command.F);
        assertEquals(1, car.getX());
        assertEquals(5, car.getY());
        assertEquals(Direction.N, car.getDirection());
    }

    @Test
    void testMoveForwardFromSouth() {
        Car car = new Car("TestCar", 1, 4, Direction.S, new Command[]{Command.F});
        carController.executeCarCommand(car, Command.F);
        assertEquals(1, car.getX());
        assertEquals(3, car.getY());
        assertEquals(Direction.S, car.getDirection());
    }

    @Test
    void testMoveForwardFromEast() {
        Car car = new Car("TestCar", 1, 4, Direction.E, new Command[]{Command.F});
        carController.executeCarCommand(car, Command.F);
        assertEquals(2, car.getX());
        assertEquals(4, car.getY());
        assertEquals(Direction.E, car.getDirection());
    }

    @Test
    void testMoveForwardFromWest() {
        Car car = new Car("TestCar", 2, 4, Direction.W, new Command[]{Command.F});
        carController.executeCarCommand(car, Command.F);
        assertEquals(1, car.getX());
        assertEquals(4, car.getY());
        assertEquals(Direction.W, car.getDirection());
    }

    @Test
    void testMoveForwardFromNorthStaysAtSamePlace() {
        Car car = new Car("TestCar", 1, 9, Direction.N, new Command[]{Command.F});
        carController.executeCarCommand(car, Command.F);
        assertEquals(1, car.getX());
        assertEquals(9, car.getY());
        assertEquals(Direction.N, car.getDirection());
    }

    @Test
    void testMoveForwardFromSouthStaysAtSamePlace() {
        Car car = new Car("TestCar", 1, 0, Direction.S, new Command[]{Command.F});
        carController.executeCarCommand(car, Command.F);
        assertEquals(1, car.getX());
        assertEquals(0, car.getY());
        assertEquals(Direction.S, car.getDirection());
    }

    @Test
    void testMoveForwardFromEastStaysAtSamePlace() {
        Car car = new Car("TestCar", 9, 4, Direction.E, new Command[]{Command.F});
        carController.executeCarCommand(car, Command.F);
        assertEquals(9, car.getX());
        assertEquals(4, car.getY());
        assertEquals(Direction.E, car.getDirection());
    }

    @Test
    void testMoveForwardFromWestStayAtSamePlace() {
        Car car = new Car("TestCar", 0, 4, Direction.W, new Command[]{Command.F});
        carController.executeCarCommand(car, Command.F);
        assertEquals(0, car.getX());
        assertEquals(4, car.getY());
        assertEquals(Direction.W, car.getDirection());
    }
}
