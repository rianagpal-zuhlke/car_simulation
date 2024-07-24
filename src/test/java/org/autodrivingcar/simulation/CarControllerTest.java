package org.autodrivingcar.simulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.autodrivingcar.model.Car;
import org.autodrivingcar.model.Direction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarControllerTest {
    private CarController carController;

    @BeforeEach
    void setUp() {
        carController = new CarController();
    }

    @Test
    void testRotateLeft() {
        Car car = new Car("TestCar", 1, 4, Direction.N, "L");
        carController.executeCarCommand(car, 'L', 10, 10);
        assertEquals(Direction.W, car.getDirection());

        carController.executeCarCommand(car, 'L', 10, 10);
        assertEquals(Direction.S, car.getDirection());

        carController.executeCarCommand(car, 'L', 10, 10);
        assertEquals(Direction.E, car.getDirection());

        carController.executeCarCommand(car, 'L', 10, 10);
        assertEquals(Direction.N, car.getDirection());
    }

    @Test
    void testRotateRight() {
        Car car = new Car("TestCar", 1, 4, Direction.N, "R");

        carController.executeCarCommand(car, 'R', 10, 10);
        assertEquals(Direction.E, car.getDirection());

        carController.executeCarCommand(car, 'R', 10, 10);
        assertEquals(Direction.S, car.getDirection());

        carController.executeCarCommand(car, 'R', 10, 10);
        assertEquals(Direction.W, car.getDirection());

        carController.executeCarCommand(car, 'R', 10, 10);
        assertEquals(Direction.N, car.getDirection());
    }

    @Test
    void testMoveForward() {
        Car car = new Car("TestCar", 2, 2, Direction.N, "F");

        carController.executeCarCommand(car, 'F', 10, 10);
        assertEquals(2, car.getX());
        assertEquals(3, car.getY());

        car.setDirection(Direction.E);
        carController.executeCarCommand(car, 'F', 10, 10);
        assertEquals(3, car.getX());
        assertEquals(3, car.getY());

        car.setDirection(Direction.S);
        carController.executeCarCommand(car, 'F', 10, 10);
        assertEquals(3, car.getX());
        assertEquals(2, car.getY());

        car.setDirection(Direction.N);
        carController.executeCarCommand(car, 'F', 10, 10);
        assertEquals(3, car.getX());
        assertEquals(3, car.getY());
    }

    @Test
    void testMoveForwardBoundsX() {
        Car car = new Car("TestCar", 9, 0, Direction.E, "F");
        carController.executeCarCommand(car, 'F', 10, 10);
        assertEquals(9, car.getX());

        car.setX(0);
        car.setDirection(Direction.W);
        carController.executeCarCommand(car, 'F', 10, 10);
        assertEquals(0, car.getX());
    }

    @Test
    void testMoveForwardBoundsY() {
        Car car = new Car("TestCar", 0, 0, Direction.S, "F");

        carController.executeCarCommand(car, 'F', 10, 10);
        assertEquals(0, car.getY());

        car.setY(9);
        car.setDirection(Direction.N);
        assertEquals(9, car.getY());
    }
}
