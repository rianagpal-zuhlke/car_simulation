package org.autodrivingcar.utils;

import org.autodrivingcar.model.Car;
import org.autodrivingcar.model.Command;
import org.autodrivingcar.model.Direction;

import java.util.ArrayList;
import java.util.List;

public class CarHelper {
    public static List<Car> generateSampleCarList() {
        List<Car> carList = new ArrayList<>();

        Car car1 = new Car("TestCar1", 1, 2, Direction.N, new Command[]{Command.F, Command.F});
        carList.add(car1);
        Car car2 = new Car("TestCar2", 3, 4, Direction.S, new Command[]{Command.L, Command.R});
        carList.add(car2);
        Car car3 = new Car("TestCar3", 5, 6, Direction.E, new Command[]{Command.F, Command.F, Command.R});
        carList.add(car3);

        return carList;
    }
}
