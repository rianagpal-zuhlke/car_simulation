package org.autodrivingcar.model;

public class Car {
    private String carName;
    private int x, y;
    private Direction direction;
    private String commands;

    public Car(String carName, int x, int y, Direction direction, String commands) {
        this.carName = carName;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.commands = commands;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
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

    public String getCommands() {
        return commands;
    }

    public void setCommands(String commands) {
        this.commands = commands;
    }
}

