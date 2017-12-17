package com.gojek.services;

import com.gojek.exceptions.VehicleNotFoundException;
import com.gojek.models.Color;
import com.gojek.models.Ticket;
import com.gojek.models.Vehicle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParkingLotService {

    private final HashMap<Integer, Vehicle> parkingLot;

    private final TicketingSystem ticketingSystem;

    public ParkingLotService(int slots) {
        this.parkingLot = new HashMap<>(slots);
        this.ticketingSystem = new TicketingSystem(slots);
    }

    public int parkVehicle(Vehicle vehicle) {
        Ticket ticket = ticketingSystem.issueTicket();
        parkingLot.put(ticket.getSlot(), vehicle);
        return ticket.getSlot();
    }

    public void removeVehicleFromSlot(int slot) {
        if (parkingLot.containsKey(slot)) {
            parkingLot.remove(slot);
            ticketingSystem.markSlotFree(new Ticket(slot));
        } else {
            throw new VehicleNotFoundException();
        }
    }

    public List<String> findCarsLicensePlatesWithColor(Color color) {
        return parkingLot
                .entrySet()
                .stream()
                .filter(element -> element.getValue().getColor() == color)
                .map(element -> element.getValue().getLicensePlate())
                .collect(Collectors.toList());
    }

    public List<Integer> findSlotNumbersForColors(Color color) {
        return parkingLot
                .entrySet()
                .stream()
                .filter(element -> element.getValue().getColor() == color)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public Integer findSlotByRegistrationNumber(String registration) {
        List<Integer> slot = parkingLot
                .entrySet()
                .stream()
                .filter(element -> element.getValue().getLicensePlate().equalsIgnoreCase(registration))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        if (slot.size() == 0) {
            throw new VehicleNotFoundException();
        }
        return slot.get(0);
    }

    public void getStatus() {
        System.out.println("Slot No\tRegistration No.\tColour");
        parkingLot.forEach((key, value) -> System.out.println(key + "\t\t" + value.getLicensePlate() + "\t\t" + value.getColor().getColor()));
    }
}
