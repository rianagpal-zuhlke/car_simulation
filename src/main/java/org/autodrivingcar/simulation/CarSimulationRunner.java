package org.autodrivingcar.simulation;

import org.autodrivingcar.model.Car;
import org.autodrivingcar.model.Command;
import org.autodrivingcar.ui.printer.SimulationResultsPrinter;

import java.io.PrintStream;

public class CarSimulationRunner {
    private CarController carController;
    private final SimulationResultsPrinter printer;

    public CarSimulationRunner(PrintStream out) {
        this.printer = new SimulationResultsPrinter(out);
    }

    public void runCarSimulation(SimulationManager simulationManager) {
        carController = new CarController(simulationManager.getFieldWidth(), simulationManager.getFieldHeight());

        int maxNumberOfCommands = getMaxNumberOfCommands(simulationManager);

        for (int step = 0; step < maxNumberOfCommands; step++) {
            if(!executeCommandsForEachCar(simulationManager, step))
                return;
        }

        printer.printSimulationResults(simulationManager.getCars());
    }

    private int getMaxNumberOfCommands(SimulationManager simulationManager) {
        return simulationManager.getCars().stream()
                .mapToInt(car -> car.getCommands().length)
                .max()
                .orElse(0);
    }

    private boolean executeCommandsForEachCar(SimulationManager simulationManager, int step) {
        for (Car car : simulationManager.getCars()) {
            if (step < car.getCommands().length) {
                Command command = car.getCommands()[step];
                if (carController.executeCarCommand(car, command)){
                    if (checkForCollision(simulationManager, car, step)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean checkForCollision(SimulationManager simulationManager, Car currentCar, int step) {
        for (Car otherCar : simulationManager.getCars()) {
            if (!otherCar.getCarName().equals(currentCar.getCarName()) &&
                    currentCar.getX() == otherCar.getX() &&
                    currentCar.getY() == otherCar.getY()) {
                printer.printSimulationResults(simulationManager.getCars(), currentCar, otherCar, step + 1);
                return true;
            }
        }
        return false;
    }
}
