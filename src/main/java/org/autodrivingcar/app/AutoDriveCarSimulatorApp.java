package org.autodrivingcar.app;

import org.autodrivingcar.simulation.CarManager;
import org.autodrivingcar.ui.UserOptionsHandler;

import java.util.Scanner;


public class AutoDriveCarSimulatorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarManager carManager = new CarManager();
        UserOptionsHandler userOptionsInterface = new UserOptionsHandler(carManager, scanner);
        userOptionsInterface.start();
    }
}
