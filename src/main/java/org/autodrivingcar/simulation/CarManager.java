package org.autodrivingcar.simulation;

import org.autodrivingcar.model.Car;
import org.autodrivingcar.ui.printer.CarPrinter;

import java.util.ArrayList;
import java.util.List;

public class CarManager {
    private final List<Car> cars = new ArrayList<>();
    private final static CarPrinter carPrinter = new CarPrinter();

    public void addCar(Car car) {
        cars.add(car);
    }

    public List<Car> getCars() {
        return cars;
    }

    public void printCarsList() {
        carPrinter.print(cars);
    }

    public void clearCars() {
        cars.clear();
    }
}
