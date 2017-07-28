package com.lunayo.parking.port.adapter.persistence;

import com.lunayo.parking.domain.model.parking.Car;
import com.lunayo.parking.domain.model.parking.CarRepository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Lunayo on 28/07/2017.
 *
 */

public class InMemoryCarRepository implements CarRepository {

    private List<Car> cars = Collections.synchronizedList(new ArrayList<Car>());

    @Override
    public void saveCar(Car car) {
        cars.add(car);
    }

    @Override
    public Collection<String> registrationNumbersOfColour(String colour) {
        return this.cars.stream().filter(c -> c.colour().equals(colour))
                .map(Car::registrationNumber).collect(Collectors.toList());
    }

    @Override
    public void removeCarOfSlotID(int slotID) {
        this.cars.removeIf(c -> c.slotID() == slotID);
    }

    @Override
    public Collection<Integer> slotNumbersOfColour(String colour) {
        return this.cars.stream().filter(c -> c.colour().equals(colour)).map(Car::slotID).collect(Collectors.toList());
    }

    @Override
    public Optional<Integer> slotNumberOfRegistrationNumber(String registrationNumber) {
        return this.cars.stream().filter(c -> c.registrationNumber().equals(registrationNumber)).findFirst().map(Car::slotID);
    }
}
