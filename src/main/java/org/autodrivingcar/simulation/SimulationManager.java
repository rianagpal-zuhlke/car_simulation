package org.autodrivingcar.simulation;

import org.autodrivingcar.model.Car;
import org.autodrivingcar.ui.printer.CarPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarManager {
    private final List<Car> cars = new ArrayList<>();
    private int fieldWidth;
    private int fieldHeight;
    private final static CarPrinter carPrinter = new CarPrinter();

    public void addCar(Car car) {
        cars.add(car);
    }

    public List<Car> getCars() {
        return cars;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public void printCarsList() {
        carPrinter.print(cars);
    }

    public void clearCars() {
        cars.clear();
    }

    public void configureField(int fieldWidth, int fieldHeight) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
    }

    public void configureField(Scanner scanner) {
        carPrinter.configureField(this, scanner);
    }
}
