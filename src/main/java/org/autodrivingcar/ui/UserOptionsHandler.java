package org.autodrivingcar.ui;

import org.autodrivingcar.model.Field;
import org.autodrivingcar.simulation.CarManager;
import org.autodrivingcar.simulation.CarSimulationRunner;
import org.autodrivingcar.ui.inputhandler.CarInputHandler;
import org.autodrivingcar.ui.inputhandler.FieldInputHandler;

import java.util.Scanner;

public class UserOptionsHandler {

    private final CarManager carManager;
    private final Scanner scanner;
    FieldInputHandler fieldInputHandler;
    CarInputHandler carInputHandler;
    private Field field;
    private boolean exitApplication = false;

    public UserOptionsHandler(CarManager carManager, Scanner scanner) {
        this.carManager = carManager;
        this.scanner = scanner;
        this.fieldInputHandler = new FieldInputHandler(scanner);
        this.carInputHandler = new CarInputHandler(scanner);
    }

    public void start() {
        field = fieldInputHandler.configureField();

        while (!exitApplication) {
            int choice = getUserChoiceBeforeSimulation();
            handleUserChoice(choice);
        }
    }

    public int getUserChoiceBeforeSimulation() {
        System.out.println("Please choose from the following options:");
        System.out.println("[1] Add a car to field");
        System.out.println("[2] Run simulation");

        return scanner.nextInt();
    }

    public int getUserChoiceAfterSimulation() {
        System.out.println("\nPlease choose from the following options:");
        System.out.println("[1] Start over");
        System.out.println("[2] Exit");

        return scanner.nextInt();
    }

    public void handleUserChoice(int choice) {
        if (choice == 1) {
            carManager.addCar(carInputHandler.createNewCar());
            carManager.printCarsList();
        } else if (choice == 2) {
            carManager.printCarsList();
            new CarSimulationRunner().runCarSimulation(carManager, field);
            handleUserChoiceAfterSimulation();
        } else {
            System.out.println("Invalid option. Please try again.");
        }
    }

    public void handleUserChoiceAfterSimulation() {
        int userChoice = getUserChoiceAfterSimulation();
        if (userChoice == 1) {
            carManager.clearCars();
        } else if (userChoice == 2) {
            System.out.println("Thank you for running the simulation. Goodbye!");
            exitApplication = true;
        } else {
            System.out.println("Invalid option. Please try again.");
        }
    }
}
