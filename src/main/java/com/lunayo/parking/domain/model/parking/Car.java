package com.lunayo.parking.domain.model.parking;

import com.lunayo.parking.domain.model.Entity;

/**
 * Created by Lunayo on 28/07/2017.
 *
 */

public class Car extends Entity {

    private int slotID;
    private String registrationNumber;
    private String colour;

    public Car(String registrationNumber, String colour) {
        this.setRegistrationNumber(registrationNumber);
        this.setColour(colour);
    }

    public void assignSlotID(int slotID) {
        this.setSlotID(slotID);
    }

    public int slotID() {
        return this.slotID;
    }

    public String colour() {
        return this.colour;
    }

    public String registrationNumber() {
        return this.registrationNumber;
    }

    private void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    private void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    private void setColour(String colour) {
        this.colour = colour;
    }
}
