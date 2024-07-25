package org.autodrivingcar.simulation;

import org.autodrivingcar.model.Car;
import org.autodrivingcar.ui.printer.CarPrinter;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class SimulationManager {
    private final List<Car> cars = new ArrayList<>();
    private int fieldWidth;
    private int fieldHeight;
    private final CarSimulationRunner carSimulationRunner;
    private final CarPrinter carPrinter;

    public SimulationManager(PrintStream out) {
        this.carPrinter = new CarPrinter(out);
        this.carSimulationRunner = new CarSimulationRunner(out);
    }

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
        carPrinter.printCarList(cars);
    }

    public void clearCars() {
        cars.clear();
    }

    public void configureField(int fieldWidth, int fieldHeight) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
    }

    public void runCarSimulation() {
        carSimulationRunner.runCarSimulation(this);
    }
}
