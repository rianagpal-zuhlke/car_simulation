package org.autodrivingcar.app;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoDriveCarSimulatorAppTest {

    @Test
    public void testWithOneCarSimulation() {

        String simulatedUserInput = "10 10\n1\nA\n1 2 N\nFFRFFFFRRL\n2\n2\n";

        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayInputStream inContent = new ByteArrayInputStream(simulatedUserInput.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        try {
            AutoDriveCarSimulatorApp.main(new String[]{});

            System.setOut(originalOut);

            String expectedOutput =
                    """
                            Welcome to Auto Driving Car Simulation!
                            Please enter the width and height of the simulation field in x y format:

                            You have created a field of 10 x 10.

                            Please choose from the following options:
                            [1] Add a car to field
                            [2] Run simulation
                            Please enter the name of the car:
                            Please enter initial position of car A in x y Direction format:
                            Please enter the commands for car A:

                            Your current list of cars are:
                            - A, (1,2) N, FFRFFFFRRL

                            Please choose from the following options:
                            [1] Add a car to field
                            [2] Run simulation

                            Your current list of cars are:
                            - A, (1,2) N, FFRFFFFRRL

                            After simulation, the result is:
                            - A, (5,4) S

                            Please choose from the following options:
                            [1] Start over
                            [2] Exit
                            Thank you for running the simulation. Goodbye!""";

            String actualOutput = outContent.toString().strip().replaceAll("\\s+", " ");
            expectedOutput = expectedOutput.strip().replaceAll("\\s+", " ");

            assertEquals(expectedOutput, actualOutput);
        } finally {
            // Restore the original System.in and System.out
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

    @Test
    public void testWithTwoCarSimulationWithCollision() {

        String simulatedUserInput = "10 10\n1\nA\n1 2 N\nFFRFFFFRRL\n1\nB\n7 8 W\nFFLFFFFFFF\n2\n2\n";

        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayInputStream inContent = new ByteArrayInputStream(simulatedUserInput.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        try {
            AutoDriveCarSimulatorApp.main(new String[]{});

            System.setOut(originalOut);

            String expectedOutput =
                    """
                            Welcome to Auto Driving Car Simulation!
                            Please enter the width and height of the simulation field in x y format:

                            You have created a field of 10 x 10.

                            Please choose from the following options:
                            [1] Add a car to field
                            [2] Run simulation
                            Please enter the name of the car:
                            Please enter initial position of car A in x y Direction format:
                            Please enter the commands for car A:

                            Your current list of cars are:
                            - A, (1,2) N, FFRFFFFRRL

                            Please choose from the following options:
                            [1] Add a car to field
                            [2] Run simulation
                            Please enter the name of the car:
                            Please enter initial position of car B in x y Direction format:
                            Please enter the commands for car B:

                            Your current list of cars are:
                            - A, (1,2) N, FFRFFFFRRL
                            - B, (7,8) W, FFLFFFFFFF

                            Please choose from the following options:
                            [1] Add a car to field
                            [2] Run simulation

                            Your current list of cars are:
                            - A, (1,2) N, FFRFFFFRRL
                            - B, (7,8) W, FFLFFFFFFF

                            After simulation, the result is:
                            - A, collides with B at (5,4) at step 7
                            - B, collides with A at (5,4) at step 7

                            Please choose from the following options:
                            [1] Start over
                            [2] Exit
                            Thank you for running the simulation. Goodbye!""";

            String actualOutput = outContent.toString().strip().replaceAll("\\s+", " ");

            expectedOutput = expectedOutput.strip().replaceAll("\\s+", " ");


            assertEquals(expectedOutput, actualOutput);
        } finally {
            // Restore the original System.in and System.out
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}
