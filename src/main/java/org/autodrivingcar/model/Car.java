package org.autodrivingcar.model;

public class Car {
    private final String carName;
    private int x, y;
    private Direction direction;
    private final Command[] commands;

    public Car(String carName, int x, int y, Direction direction, Command[] commands) {
        this.carName = carName;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.commands = commands;
    }

    public String getCarName() {
        return carName;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Command[] getCommands() {
        return commands;
    }
}

