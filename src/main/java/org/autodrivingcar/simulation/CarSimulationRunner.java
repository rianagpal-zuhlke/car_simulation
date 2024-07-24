package org.autodrivingcar.simulation;

import org.autodrivingcar.model.Car;
import org.autodrivingcar.model.Field;
import org.autodrivingcar.ui.printer.SimulationResultsPrinter;

public class CarSimulationRunner {
    private final CarController carController = new CarController();
    private final SimulationResultsPrinter printer = new SimulationResultsPrinter();

    public void runCarSimulation(CarManager carManager, Field field) {
        int maxNumberOfCommands = carManager.getCars().stream().mapToInt(car -> car.getCommands().length()).max().orElse(0);

        for (int step = 0; step < maxNumberOfCommands; step++) {
            for (Car car : carManager.getCars()) {
                if (step < car.getCommands().length()) {
                    char command = car.getCommands().charAt(step);
                    if (carController.executeCarCommand(car, command, field.getWidth(), field.getHeight())) {
                        for (Car otherCar : carManager.getCars()) {
                            if (!otherCar.getCarName().equals(car.getCarName())) {
                                if (car.getX() == otherCar.getX() && car.getY() == otherCar.getY()) {
                                    printer.printSimulationResults(carManager.getCars(), car, otherCar, step+1);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
        printer.printSimulationResults(carManager.getCars());
    }
}
