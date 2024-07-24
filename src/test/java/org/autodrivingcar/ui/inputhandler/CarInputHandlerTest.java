package org.autodrivingcar.ui.inputhandler;

import org.autodrivingcar.model.Car;
import org.autodrivingcar.model.Direction;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarInputHandlerTest {

    @Test
    public void testCreateNewCar() {
        String input = "\nCar1\n10 20 N\nLFR\n";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        CarInputHandler carInputHandler = new CarInputHandler(scanner);


        Car car = carInputHandler.createNewCar();

        assertEquals("Car1", car.getCarName());
        assertEquals(10, car.getX());
        assertEquals(20, car.getY());
        assertEquals(Direction.N, car.getDirection());
        assertEquals("LFR", car.getCommands());
    }
}
