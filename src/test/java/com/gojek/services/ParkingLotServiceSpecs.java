package com.gojek.services;

import com.gojek.exceptions.TicketNotIssuedException;
import com.gojek.exceptions.VehicleNotFoundException;
import com.gojek.models.Color;
import com.gojek.models.Vehicle;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ParkingLotServiceSpecs {

    @Test
    public void vehiclesShouldPark() {
        ParkingLotService parkingLotService = new ParkingLotService(6);
        parkingLotService.parkVehicle(new Vehicle("UP 13 AX 1386", Color.WHITE));
        parkingLotService.parkVehicle(new Vehicle("UP 13 AX 9386", Color.BLACK));
        parkingLotService.parkVehicle(new Vehicle("UP 13 AX 9396", Color.BLUE));
        parkingLotService.parkVehicle(new Vehicle("UP 13 AX 5386", Color.RED));
        parkingLotService.parkVehicle(new Vehicle("UP 13 AX 9486", Color.WHITE));
        parkingLotService.parkVehicle(new Vehicle("UP 13 AX 9388", Color.WHITE));
        parkingLotService.getStatus();
    }

    @Test(expected = TicketNotIssuedException.class)
    public void shouldNotParkMoreVehicles() {

        ParkingLotService parkingLotService = new ParkingLotService(6);
        parkingLotService.parkVehicle(new Vehicle("UP 14 AX 1386", Color.WHITE));
        parkingLotService.parkVehicle(new Vehicle("UP 14 AX 9386", Color.BLACK));
        parkingLotService.parkVehicle(new Vehicle("UP 15 AX 9396", Color.BLUE));
        parkingLotService.parkVehicle(new Vehicle("UP 16 AX 5386", Color.RED));
        parkingLotService.parkVehicle(new Vehicle("UP 17 AX 9486", Color.WHITE));
        parkingLotService.parkVehicle(new Vehicle("UP 18 AX 9388", Color.WHITE));
        parkingLotService.parkVehicle(new Vehicle("AP 18 AX 9388", Color.WHITE));
    }

    @Test(expected = VehicleNotFoundException.class)
    public void shouldThrowVehicleNotFoundException(){
        ParkingLotService parkingLotService = new ParkingLotService(6);
        parkingLotService.removeVehicleFromSlot(5);
    }

    @Test
    public void shouldParkVehicleAndRemove(){
        ParkingLotService parkingLotService = new ParkingLotService(1);
        parkingLotService.parkVehicle(new Vehicle("UP 14 AX 1386", Color.WHITE));
        parkingLotService.removeVehicleFromSlot(1);
    }

    @Test
    public void findCarsWithColorsSpec() {
        ParkingLotService parkingLotService = new ParkingLotService(6);
        parkingLotService.parkVehicle(new Vehicle("UP 13 AX 1386", Color.WHITE));
        parkingLotService.parkVehicle(new Vehicle("UP 13 AX 9386", Color.BLACK));
        parkingLotService.parkVehicle(new Vehicle("UP 13 AX 9396", Color.BLUE));
        parkingLotService.parkVehicle(new Vehicle("UP 13 AX 5386", Color.RED));
        parkingLotService.parkVehicle(new Vehicle("UP 13 AX 9486", Color.WHITE));
        parkingLotService.parkVehicle(new Vehicle("UP 13 AX 9388", Color.WHITE));
        List<String> licensePlates = parkingLotService.findCarsLicensePlatesWithColor(Color.WHITE);
        licensePlates.forEach(System.out::println);
    }

    @Test
    public void findSlotByRegistrationNumberSpec() {
        ParkingLotService parkingLotService = new ParkingLotService(6);
        parkingLotService.parkVehicle(new Vehicle("UP 13 AX 1386", Color.WHITE));
        parkingLotService.parkVehicle(new Vehicle("UP 13 AX 9386", Color.BLACK));
        parkingLotService.parkVehicle(new Vehicle("UP 13 AX 9396", Color.BLUE));
        parkingLotService.parkVehicle(new Vehicle("UP 13 AX 5386", Color.RED));
        parkingLotService.parkVehicle(new Vehicle("UP 13 AX 9486", Color.WHITE));
        parkingLotService.parkVehicle(new Vehicle("UP 13 AX 9388", Color.WHITE));
        Integer slot = parkingLotService.findSlotByRegistrationNumber("UP 13 AX 9396");
        assertTrue("Slot should be 3", slot == 3);
    }

    @Test
    public void findSlotNumbersForColorsSpec() {
        ParkingLotService parkingLotService = new ParkingLotService(6);
        parkingLotService.parkVehicle(new Vehicle("UP 13 AX 1386", Color.WHITE));
        parkingLotService.parkVehicle(new Vehicle("UP 13 AX 9386", Color.BLACK));
        parkingLotService.parkVehicle(new Vehicle("UP 13 AX 9396", Color.BLUE));
        parkingLotService.parkVehicle(new Vehicle("UP 13 AX 5386", Color.RED));
        parkingLotService.parkVehicle(new Vehicle("UP 13 AX 9486", Color.WHITE));
        parkingLotService.parkVehicle(new Vehicle("UP 13 AX 9388", Color.WHITE));
        List<Integer> slots = parkingLotService.findSlotNumbersForColors(Color.WHITE);
        assertTrue("slots should have size 3", slots.size() == 3);
        System.out.println(slots);
    }
}
