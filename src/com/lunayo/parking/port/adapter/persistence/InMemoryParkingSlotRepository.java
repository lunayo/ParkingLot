package com.lunayo.parking.port.adapter.persistence;

import com.lunayo.parking.domain.model.parking.ParkingSlot;
import com.lunayo.parking.domain.model.parking.ParkingSlotRepository;

/**
 * Created by Lunayo on 28/07/2017.
 *
 */
public class InMemoryParkingSlotRepository implements ParkingSlotRepository {

    private ParkingSlot parkingSlot;

    @Override
    public ParkingSlot parkingSlot() {
        return this.parkingSlot;
    }

    @Override
    public void save(ParkingSlot parkingSlot) {
        this.parkingSlot = parkingSlot;
    }
}
