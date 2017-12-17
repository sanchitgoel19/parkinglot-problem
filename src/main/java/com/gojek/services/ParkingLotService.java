package com.gojek.services;

import com.gojek.exceptions.TicketNotIssuedException;
import com.gojek.exceptions.VehicleNotFoundException;
import com.gojek.models.Ticket;
import com.gojek.models.Vehicle;

import java.util.HashMap;

public class ParkingLotService {

    private final HashMap<Integer, Vehicle> parkingLot;

    private final TicketingSystem ticketingSystem;

    public ParkingLotService(int slots) {
        this.parkingLot = new HashMap<>(slots);
        this.ticketingSystem = new TicketingSystem(slots);
    }

    public void parkVehicle(Vehicle vehicle) {
        Ticket ticket = ticketingSystem.issueTicket();
        parkingLot.put(ticket.getSlot(), vehicle);
    }

    public void removeVehicleFromSlot(int slot) {
        if (parkingLot.containsKey(slot)) {
            parkingLot.remove(slot);
            ticketingSystem.markSlotFree(new Ticket(slot));
        } else {
            throw new VehicleNotFoundException();
        }
    }
}
