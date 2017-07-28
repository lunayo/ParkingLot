package com.lunayo.parking.domain.model.parking;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Lunayo on 28/07/2017.
 *
 */

public interface CarRepository {

    public void saveCar(Car car);

    public void removeCarOfSlotID(int slotID);

    public Collection<String> registrationNumbersOfColour(String colour);

    public Collection<Integer> slotNumbersOfColour(String colour);

    public Optional<Integer> slotNumberOfRegistrationNumber(String registrationNumber);
}
