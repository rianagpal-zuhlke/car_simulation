package org.autodrivingcar.ui.inputhandler;

import org.autodrivingcar.model.Field;

import java.util.Scanner;

public class FieldInputHandler {
    private final Scanner scanner;

    public FieldInputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public Field configureField() {
        System.out.println("Welcome to Auto Driving Car Simulation!");
        System.out.println("Please enter the width and height of the simulation field in x y format: ");

        int width = scanner.nextInt();
        int height = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nYou have created a field of " + width + " x " + height + ".\n");

        return new Field(width, height);
    }
}
