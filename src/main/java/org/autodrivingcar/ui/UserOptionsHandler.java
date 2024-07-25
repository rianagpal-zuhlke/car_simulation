package org.autodrivingcar.ui;

import org.autodrivingcar.simulation.SimulationManager;
import org.autodrivingcar.ui.printer.UserOptionsPrinter;

import java.io.PrintStream;
import java.util.Scanner;

public class UserOptionsHandler {

    private final SimulationManager simulationManager;
    private final Scanner scanner;
    private final CarOptionsHandler carOptionsHandler;
    private final UserOptionsPrinter userOptionsPrinter;

    private boolean exitApplication = false;

    public UserOptionsHandler(SimulationManager simulationManager, Scanner scanner, PrintStream out) {
        this.simulationManager = simulationManager;
        this.scanner = scanner;
        this.carOptionsHandler = new CarOptionsHandler(scanner, out);
        this.userOptionsPrinter = new UserOptionsPrinter(out);
    }

    public void start() {
        carOptionsHandler.configureField(simulationManager);

        while (!exitApplication) {
            userOptionsPrinter.printUserChoiceBeforeSimulation();
            handleUserChoice(scanner.nextInt());
        }
    }

    public void handleUserChoice(int choice) {
        if (choice == 1) {
            addNewCar();
        } else if (choice == 2) {
            runSimulation();
            handleUserChoiceAfterSimulation();
        } else {
            userOptionsPrinter.printInvalidChoiceMessage();
        }
    }

    public void handleUserChoiceAfterSimulation() {
        userOptionsPrinter.printUserChoiceAfterSimulation();
        int userChoiceAfterSimulation = scanner.nextInt();

        if (userChoiceAfterSimulation == 1) {
            simulationManager.clearCars();
        } else if (userChoiceAfterSimulation == 2) {
            exitApplication();
        } else {
            userOptionsPrinter.printInvalidChoiceMessage();
        }
    }

    private void addNewCar() {
        simulationManager.addCar(carOptionsHandler.configureNewCar());
        simulationManager.printCarsList();
    }

    private void runSimulation() {
        simulationManager.printCarsList();
        simulationManager.runCarSimulation();
    }

    private void exitApplication() {
        userOptionsPrinter.printApplicationExitMessage();
        exitApplication = true;
    }
}
