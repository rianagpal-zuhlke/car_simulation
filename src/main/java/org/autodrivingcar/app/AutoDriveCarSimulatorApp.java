package org.autodrivingcar.app;

import org.autodrivingcar.simulation.SimulationManager;
import org.autodrivingcar.ui.UserOptionsHandler;

import java.io.PrintStream;
import java.util.Scanner;


public class AutoDriveCarSimulatorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrintStream out = System.out;

        SimulationManager simulationManager = new SimulationManager(out);
        UserOptionsHandler userOptionsInterface = new UserOptionsHandler(simulationManager, scanner, out);

        userOptionsInterface.start();
    }
}
