package org.autodrivingcar.ui.printer;

import org.autodrivingcar.model.Car;

import java.io.PrintStream;
import java.util.List;
import java.util.Objects;

public class SimulationResultsPrinter {
    PrintStream out;

    public SimulationResultsPrinter(PrintStream out) {
        this.out = out;
    }

    public void printSimulationResults(List<Car> cars, Car collisionCar1, Car collisionCar2, int step) {
        out.println("After simulation, the result is:");
        cars.forEach(car -> {
            if(Objects.equals(car.getCarName(), collisionCar1.getCarName()))
                out.printf("- %s, collides with %s at (%d,%d) at step %d%n", car.getCarName(), collisionCar2.getCarName(), car.getX(), car.getY(), step);
            else if(Objects.equals(car.getCarName(), collisionCar2.getCarName()))
                out.printf("- %s, collides with %s at (%d,%d) at step %d%n", car.getCarName(), collisionCar1.getCarName(), car.getX(), car.getY(), step);
            else
                out.printf("- %s, (%d,%d) %s%n", car.getCarName(), car.getX(), car.getY(), car.getDirection());
        });
    }

    public void printSimulationResults(List<Car> cars) {
        out.println("After simulation, the result is:");
        cars.forEach(car ->
                out.printf("- %s, (%d,%d) %s%n", car.getCarName(), car.getX(), car.getY(), car.getDirection())
        );
    }
}
