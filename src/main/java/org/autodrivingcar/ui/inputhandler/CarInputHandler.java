package org.autodrivingcar.ui.inputhandler;

import org.autodrivingcar.model.Car;
import org.autodrivingcar.model.Direction;

import java.util.Scanner;

public class CarInputHandler {
    private final Scanner scanner;

    public CarInputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public Car createNewCar() {
        System.out.println("Please enter the name of the car:");
        scanner.nextLine();
        String carName = scanner.nextLine();

        System.out.println("Please enter initial position of car " + carName + " in x y Direction format:");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        Direction direction = Direction.valueOf(scanner.next());
        scanner.nextLine();

        System.out.println("Please enter the commands for car " + carName + ":");
        String commands = scanner.nextLine();

        return new Car(carName, x, y, direction, commands);
    }

}
