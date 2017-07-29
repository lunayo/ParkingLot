package com.lunayo.parking.application;

import com.lunayo.parking.domain.model.parking.Car;
import com.lunayo.parking.domain.model.parking.CarRepository;
import com.lunayo.parking.domain.model.parking.ParkingSlot;
import com.lunayo.parking.domain.model.parking.ParkingSlotRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Lunayo on 28/07/2017.
 *
 */

public class ParkingLotService {

    private CarRepository carRepository;
    private ParkingSlotRepository parkingSlotRepository;

    public ParkingLotService(CarRepository carRepository, ParkingSlotRepository parkingSlotRepository) {
        this.carRepository = carRepository;
        this.parkingSlotRepository = parkingSlotRepository;
    }

    public void createParkingSlot(int maxSlot) {
        ParkingSlot parkingSlot = new ParkingSlot(maxSlot);

        parkingSlotRepository.save(parkingSlot);

        System.out.println(String.format("Created a parking lot with %d slots", maxSlot));
    }

    public void allocateParkingSlotToCar(String registrationNumber, String colour) {
        try {
            ParkingSlot parkingSlot = parkingSlotRepository.parkingSlot();

            Car aCar = new Car(registrationNumber, colour);
            aCar.assignSlotID(parkingSlot.nextSlot());

            carRepository.saveCar(aCar);

            System.out.println(String.format("Allocated slot number: %d", aCar.slotID()));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, parking lot is full");
        }
    }

    public void deallocateParkingSlot(int slotID) {
        try {
            ParkingSlot parkingSlot = parkingSlotRepository.parkingSlot();

            carRepository.removeCarOfSlotID(slotID);
            parkingSlot.deallocateSlot(slotID);

            System.out.println(String.format("Slot number %d is free", slotID));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void parkingStatus() {
        Collection<Car> cars = carRepository.listOfCars();
        System.out.println("Slot No.\tRegistration No\tColour");
        for(Car c: cars) {
            System.out.println(String.format("%d\t%s\t%s",c.slotID(), c.registrationNumber(), c.colour()));
        }
    }

    public void listRegistrationNumberOfCars(String colour) {
        Collection<String> colours = carRepository.registrationNumbersOfColour(colour);
        System.out.println(String.join(", ", colours));
    }

    public void listSlotNumberOfCars(String colour) {
        Collection<String> slotIDs =
                carRepository.slotNumbersOfColour(colour).stream()
                        .map(s -> s.toString()).collect(Collectors.toList());
        System.out.println(String.join(", ", slotIDs));
    }

    public void slotNumberOfCar(String registrationNumber) {
        Optional<Integer> slotID = carRepository.slotNumberOfRegistrationNumber(registrationNumber);
        System.out.println(slotID.map(s -> s.toString()).orElse("Not found"));
    }

}
