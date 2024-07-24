package org.autodrivingcar.simulation;

import org.autodrivingcar.model.Car;
import org.autodrivingcar.model.Direction;

public class CarController {
    private void rotateLeft(Car car) {
        switch (car.getDirection()) {
            case N:
                car.setDirection(Direction.W);
                break;
            case W:
                car.setDirection(Direction.S);
                break;
            case S:
                car.setDirection(Direction.E);
                break;
            case E:
                car.setDirection(Direction.N);
                break;
        }
    }

    private void rotateRight(Car car) {
        switch (car.getDirection()) {
            case N:
                car.setDirection(Direction.E);
                break;
            case E:
                car.setDirection(Direction.S);
                break;
            case S:
                car.setDirection(Direction.W);
                break;
            case W:
                car.setDirection(Direction.N);
                break;
        }
    }

    private void moveForward(Car car, int fieldWidth, int fieldHeight) {

        int carX = car.getX();
        int carY = car.getY();

        switch (car.getDirection()) {
            case N:
                if (carY < fieldHeight - 1)
                    car.setY(++carY);
                break;
            case E:
                if (carX < fieldWidth - 1)
                    car.setX(++carX);
                break;
            case S:
                if (carY > 0)
                    car.setY(--carY);
                break;
            case W:
                if (carX > 0)
                    car.setX(--carX);
                break;
        }
    }

    public boolean executeCarCommand(Car car, char command, int fieldWidth, int fieldHeight) {
        switch (command) {
            case 'L':
                rotateLeft(car);
                break;
            case 'R':
                rotateRight(car);
                break;
            case 'F':
                moveForward(car, fieldWidth, fieldHeight);
                break;
        }
        return true;
    }
}

