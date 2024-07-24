package org.autodrivingcar.ui.printer;

import org.autodrivingcar.model.Car;

import java.util.List;

public class CarPrinter {
    public void print(List<Car> cars) {
        System.out.println();
        System.out.println("Your current list of cars are:");
        cars.forEach(car ->
                System.out.println("- " + car.getCarName() + ", (" + car.getX() + "," + car.getY() + ") " + car.getDirection() + ", " + car.getCommands()));
        System.out.println();
    }
}
