package org.autodrivingcar.ui.printer;

import org.autodrivingcar.model.Car;

import java.util.List;
import java.util.Objects;

public class SimulationResultsPrinter {
    public void printSimulationResults(List<Car> cars, Car collisionCar1, Car collisionCar2, int step) {
        System.out.println("\nAfter simulation, the result is:");
        cars.forEach(car -> {
            if(Objects.equals(car.getCarName(), collisionCar1.getCarName()))
                System.out.println("- " + car.getCarName() + ", collides with " + collisionCar2.getCarName() + " at (" + car.getX() + "," + car.getY() + ") at step " + step);
            else if(Objects.equals(car.getCarName(), collisionCar2.getCarName()))
                System.out.println("- " + car.getCarName() + ", collides with " + collisionCar1.getCarName() + " at (" + car.getX() + "," + car.getY() + ") at step " + step);
            else
                System.out.println("- " + car.getCarName() + ", (" + car.getX() + "," + car.getY() + ") " + car.getDirection());
        });
    }

    public void printSimulationResults(List<Car> cars) {
        System.out.println("\nAfter simulation, the result is:");
        cars.forEach(car ->
                System.out.println("- " + car.getCarName() + ", (" + car.getX() + "," + car.getY() + ") " + car.getDirection())
        );
    }
}
