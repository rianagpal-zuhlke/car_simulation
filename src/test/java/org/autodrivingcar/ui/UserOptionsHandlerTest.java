package org.autodrivingcar.ui;

import org.autodrivingcar.simulation.CarManager;
import org.autodrivingcar.ui.inputhandler.CarInputHandler;
import org.autodrivingcar.ui.inputhandler.FieldInputHandler;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class UserOptionsHandlerTest {

    @Test
    public void testAddCarToField() {
        String input = "5 5\n1\nTestCar\n0 0 N\nL\n2\n2\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Scanner scanner = new Scanner(System.in);
        CarManager carManager = new CarManager();
        UserOptionsHandler userOptionsHandler = new UserOptionsHandler(carManager, scanner);

        userOptionsHandler.fieldInputHandler = new FieldInputHandler(scanner);
        userOptionsHandler.carInputHandler = new CarInputHandler(scanner);

        userOptionsHandler.start();

        String expectedOutput = """
                Welcome to Auto Driving Car Simulation!
                Please enter the width and height of the simulation field in x y format:\s

                You have created a field of 5 x 5.

                Please choose from the following options:
                [1] Add a car to field
                [2] Run simulation
                Please enter the name of the car:
                Please enter initial position of car TestCar in x y Direction format:
                Please enter the commands for car TestCar:

                Your current list of cars are:
                - TestCar, (0,0) N, L

                Please choose from the following options:
                [1] Add a car to field
                [2] Run simulation

                Your current list of cars are:
                - TestCar, (0,0) N, L


                After simulation, the result is:
                - TestCar, (0,0) W

                Please choose from the following options:
                [1] Start over
                [2] Exit
                Thank you for running the simulation. Goodbye!
                """;

        String actualOutput = outputStream.toString().strip().replaceAll("\\s+", " ");
        expectedOutput = expectedOutput.strip().replaceAll("\\s+", " ");

        assertEquals(expectedOutput, actualOutput);

        scanner.close();
    }


}
