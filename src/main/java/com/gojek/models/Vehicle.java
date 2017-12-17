package com.gojek.models;

public class Vehicle {

    private final String licensePlate;

    private final Color color;

    public Vehicle(String licensePlate, Color color) {
        this.licensePlate = licensePlate;
        this.color = color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Vehicle( licensePlate -> " + licensePlate + ", color ->" + color + ')';
    }
}
