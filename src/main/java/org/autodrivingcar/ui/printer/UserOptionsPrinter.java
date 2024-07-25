package org.autodrivingcar.ui.printer;


import java.io.PrintStream;

public class UserOptionsPrinter {
    PrintStream out;

    public UserOptionsPrinter(PrintStream out) {
        this.out = out;
    }

    public void printUserChoiceBeforeSimulation() {
        out.println("Please choose from the following options:");
        out.println("[1] Add a car to field");
        out.println("[2] Run simulation");
    }

    public void printUserChoiceAfterSimulation() {
        out.println("\nPlease choose from the following options:");
        out.println("[1] Start over");
        out.println("[2] Exit");
    }

    public void printInvalidChoiceMessage() {
        out.println("Invalid option. Please try again.");
    }

    public void printApplicationExitMessage() {
        out.println("Thank you for running the simulation. Goodbye!");
    }
}
