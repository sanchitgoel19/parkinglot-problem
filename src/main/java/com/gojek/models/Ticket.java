package com.gojek.models;

public class Ticket {

    private final Integer slot;

    public Ticket(Integer slot) {
        this.slot = slot;
    }

    public Integer getSlot() {
        return slot;
    }

    @Override
    public String toString() {
        return "Ticket(slot -> " + slot + ")";
    }
}
