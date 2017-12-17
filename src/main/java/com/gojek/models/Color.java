package com.gojek.models;

public enum Color {

    BLACK("Black"), WHITE("White"), RED("Red"), BLUE("Blue");

    private final String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public static Color valueByName(String name) {
        return Color.valueOf(name.toUpperCase());
    }
}
