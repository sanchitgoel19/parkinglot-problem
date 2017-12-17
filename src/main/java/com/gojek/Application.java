package com.gojek;

import com.gojek.exceptions.TicketNotIssuedException;
import com.gojek.exceptions.VehicleNotFoundException;
import com.gojek.models.Color;
import com.gojek.models.Operation;
import com.gojek.models.Vehicle;
import com.gojek.services.ParkingLotService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Application {

    private static final String INPUT_FILE = "input.txt";

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(INPUT_FILE);
        System.setIn(fileInputStream);
        Scanner scanner = new Scanner(System.in);
        ParkingLotService parkingLotService = null;
        while (scanner.hasNext()) {
            String inputLine = scanner.nextLine();
            String[] inputs = inputLine.split(" ");
            Operation operation = Operation.valueByName(inputs[0]);
            switch (operation) {
                case CREATE_PARKING_LOT:
                    parkingLotService = new ParkingLotService(Integer.valueOf(inputs[1]));
                    System.out.println("Created a parking lot with " + inputs[1] + " slots");
                    break;
                case PARK:
                    try {
                        int slot = parkingLotService.parkVehicle(new Vehicle(inputs[1], Color.valueByName(inputs[2])));
                        System.out.println("Allocated slot number: " + slot);
                    } catch (TicketNotIssuedException exception) {
                        System.out.println("Sorry, parking lot is full");
                    }
                    break;
                case LEAVE:
                    parkingLotService.removeVehicleFromSlot(Integer.valueOf(inputs[1]));
                    System.out.println("Slot number " + inputs[1] + " is free");
                    break;
                case STATUS:
                    parkingLotService.getStatus();
                    break;
                case SLOT_NUMBERS_FOR_CARS_WITH_COLOUR:
                    List<Integer> slots = parkingLotService.findSlotNumbersForColors(Color.valueByName(inputs[1]));
                    String result = slots.stream().map(Object::toString).collect(Collectors.joining(","));
                    System.out.println(result);
                    break;
                case SLOT_NUMBER_FOR_REGISTRATION_NUMBER:
                    try {
                        Integer slotNumber = parkingLotService.findSlotByRegistrationNumber(inputs[1]);
                        System.out.println(slotNumber);
                    } catch (VehicleNotFoundException ex) {
                        System.out.println("Not found");
                    }
                    break;
                case REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR:
                    List<String> registrations = parkingLotService.findCarsLicensePlatesWithColor(Color.valueByName(inputs[1]));
                    String registrationValues = registrations.stream().collect(Collectors.joining(","));
                    System.out.println(registrationValues);
                    break;
            }
        }
    }
}
