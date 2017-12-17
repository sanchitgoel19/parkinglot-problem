package com.gojek.models;

public enum Operation {

    CREATE_PARKING_LOT("create_parking_lot"),
    PARK("park"),
    LEAVE("leave"),
    STATUS("status"),
    REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR("registration_numbers_for_cars_with_colour"),
    SLOT_NUMBERS_FOR_CARS_WITH_COLOUR("slot_numbers_for_cars_with_colour"),
    SLOT_NUMBER_FOR_REGISTRATION_NUMBER("slot_number_for_registration_number");

    private final String inputOperation;

    Operation(String inputOperation) {
        this.inputOperation = inputOperation;
    }

    public String getInputOperation() {
        return inputOperation;
    }

    public static Operation valueByName(String name) {
        return Operation.valueOf(name.toUpperCase());
    }
}
