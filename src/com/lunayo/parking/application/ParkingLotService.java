package com.lunayo.parking.application;

import com.lunayo.parking.domain.model.parking.Car;
import com.lunayo.parking.domain.model.parking.CarRepository;
import com.lunayo.parking.domain.model.parking.ParkingSlot;
import com.lunayo.parking.domain.model.parking.ParkingSlotRepository;

import java.util.Collection;
import java.util.Optional;

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
    }

    public void allocateParkingSlotToCar(String registrationNumber, String colour) {
        ParkingSlot parkingSlot = parkingSlotRepository.parkingSlot();

        Car aCar = new Car(registrationNumber, colour);
        aCar.assignSlotID(parkingSlot.nextSlot());

        carRepository.saveCar(aCar);
    }

    public void deallocateParkingSlot(int slotID) {
        ParkingSlot parkingSlot = parkingSlotRepository.parkingSlot();

        carRepository.removeCarOfSlotID(slotID);
        parkingSlot.deallocateSlot(slotID);
    }

    public void parkingStatus() {

    }

    public void listRegistrationNumberOfCars(String colour) {
        Collection<String> colours = carRepository.registrationNumbersOfColour(colour);
    }

    public void listSlotNumberOfCars(String colour) {
        Collection<Integer> slotIDs = carRepository.slotNumbersOfColour(colour);
    }

    public void listSlotNumberOfOfCar(String registrationNumber) {
        Optional<Integer> slotID = carRepository.slotNumberOfRegistrationNumber(registrationNumber);
    }

}
