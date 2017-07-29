package com.lunayo.parking.domain.model.parking;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Lunayo on 29/07/2017.
 *
 */
public class ParkingSlotTest {

    @Test
    public void nextSlot() throws Exception {
        ParkingSlot parkingSlot = new ParkingSlot(10);
        assertEquals(1, parkingSlot.nextSlot());
        parkingSlot.nextSlot();
        parkingSlot.nextSlot();
        assertEquals(4, parkingSlot.nextSlot());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void nextSlotExceed() throws Exception {
        ParkingSlot parkingSlot = new ParkingSlot(5);
        for(int i=0;i<6;++i) {
            parkingSlot.nextSlot();
        }
    }

    @Test
    public void deallocateSlot() throws Exception {
        ParkingSlot parkingSlot = new ParkingSlot(5);
        for(int i=0;i<3;++i) {
            parkingSlot.nextSlot();
        }
        assertEquals(4, parkingSlot.nextSlot());
        parkingSlot.deallocateSlot(2);
        assertEquals(2, parkingSlot.nextSlot());
        parkingSlot.deallocateSlot(4);
        parkingSlot.deallocateSlot(1);
        assertEquals(1, parkingSlot.nextSlot());
        assertEquals(4, parkingSlot.nextSlot());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void deallocateSlotExceed() throws Exception {
        ParkingSlot parkingSlot = new ParkingSlot(5);
        parkingSlot.deallocateSlot(6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deallocateSlotDuplicate() throws Exception {
        ParkingSlot parkingSlot = new ParkingSlot(5);
        parkingSlot.nextSlot();
        parkingSlot.deallocateSlot(1);
        parkingSlot.deallocateSlot(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deallocateSlotBeforeAllocating() throws Exception {
        ParkingSlot parkingSlot = new ParkingSlot(5);
        parkingSlot.deallocateSlot(4);
    }
}