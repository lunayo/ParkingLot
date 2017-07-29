package com.lunayo.parking.domain.model.parking;

/**
 * Created by Lunayo on 28/07/2017.
 *
 */

public interface ParkingSlotRepository {

    public void save(ParkingSlot parkingSlot);

    public ParkingSlot parkingSlot();
}
