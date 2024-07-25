package org.autodrivingcar.ui.printer;

import org.autodrivingcar.model.Car;
import org.autodrivingcar.model.Command;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CarPrinter {
    PrintStream out;

    public CarPrinter(PrintStream out) {
        this.out = out;
    }

    public void printCarList(List<Car> cars) {
        out.println("\nYour current list of cars are:");
        cars.forEach(car ->
                out.printf("- %s, (%d,%d) %s, %s%n", car.getCarName(), car.getX(), car.getY(), car.getDirection(), getCarCommandsString(car.getCommands()))
        );
        out.println();
    }

    public void printWelcomeMessage() {
        out.println("Welcome to Auto Driving Car Simulation!");
        out.println("Please enter the width and height of the simulation field in x y format: ");
    }

    public void printFieldConfiguration(int fieldWidth, int fieldHeight) {
        out.printf("\nYou have created a field of %d x %d.\n%n", fieldWidth, fieldHeight);
    }

    public void askForCarName() {
        out.println("Please enter the name of the car:");
    }

    public void askForCarPosition(String carName) {
        out.printf("Please enter initial position of car %s in x y Direction format:%n", carName);
    }

    public void askForCarCommands(String carName) {
        out.printf("Please enter the commands for car %s:%n", carName);
    }

    private String getCarCommandsString(Command[] commands) {
        return Arrays.stream(commands)
                .map(Command::name)
                .collect(Collectors.joining());
    }
}
