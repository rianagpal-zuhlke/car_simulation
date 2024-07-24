package org.autodrivingcar.ui.printer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.autodrivingcar.model.Car;
import org.autodrivingcar.model.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarPrinterTest {

    @Test
    public void testPrint() {
        CarPrinter carPrinter = new CarPrinter();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        Car car1 = new Car("TestCar1", 1, 2, Direction.N, "FF");
        Car car2 = new Car("TestCar2", 3, 4, Direction.S, "LR");
        Car car3 = new Car("TestCar3", 5, 6, Direction.E, "FFR");

        carPrinter.print(Arrays.asList(car1, car2, car3));

        System.setOut(originalOut);

        String expectedOutput = """

                Your current list of cars are:
                - TestCar1, (1,2) N, FF
                - TestCar2, (3,4) S, LR
                - TestCar3, (5,6) E, FFR

                """;

        String actualOutput = outputStream.toString().strip().replaceAll("\\s+", " ");
        expectedOutput = expectedOutput.strip().replaceAll("\\s+", " ");

        assertEquals(expectedOutput, actualOutput);
    }
}
