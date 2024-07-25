package org.autodrivingcar.ui;

import org.autodrivingcar.model.Car;
import org.autodrivingcar.model.Command;
import org.autodrivingcar.model.Direction;
import org.autodrivingcar.simulation.SimulationManager;
import org.autodrivingcar.ui.printer.CarPrinter;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class CarOptionsHandler {
    private final Scanner scanner;
    private final CarPrinter carPrinter;

    public CarOptionsHandler(Scanner scanner, PrintStream out) {
        this.scanner = scanner;
        this.carPrinter = new CarPrinter(out);
    }

    public void configureField(SimulationManager simulationManager) {
        carPrinter.printWelcomeMessage();

        int width;
        int height;

        while (true) {
            width = scanner.nextInt();
            height = scanner.nextInt();

            if (width > 0 && height > 0) {
                break;
            } else {
                carPrinter.printInvalidFieldConfiguration();
            }
        }

        simulationManager.configureField(width, height);
        scanner.nextLine();

        carPrinter.printFieldConfiguration(width, height);
    }

    public Car configureNewCar() {
        carPrinter.askForCarName();
        scanner.nextLine();
        String carName = scanner.nextLine();

        carPrinter.askForCarPosition(carName);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        Direction direction = Direction.valueOf(scanner.next());
        scanner.nextLine();

        carPrinter.askForCarCommands(carName);
        String commands = scanner.nextLine();

        return new Car(carName, x, y, direction, getCarCommandsArray(commands));
    }

    private Command[] getCarCommandsArray(String commands) {
        return Arrays.stream(commands.split(""))
                .map(Command::valueOf)
                .toArray(Command[]::new);
    }
}
