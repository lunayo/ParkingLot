package com.lunayo.parking.application;

import com.lunayo.parking.port.adapter.persistence.InMemoryCarRepository;
import com.lunayo.parking.port.adapter.persistence.InMemoryParkingSlotRepository;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by Lunayo on 29/07/2017.
 *
 */
public class ParkingLotServiceTest {

    private ParkingLotService parkingLotService;

    @Before
    public void setUp() {
        this.parkingLotService = new ParkingLotService(
                new InMemoryCarRepository(), new InMemoryParkingSlotRepository());
    }

    @Test
    public void createParkingSlot() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        this.parkingLotService.createParkingSlot(10);
        assertEquals(String.format("Created a parking lot with %d slots\n", 10), outContent.toString());
    }

    @Test
    public void allocateParkingSlotToCar() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        this.parkingLotService.createParkingSlot(2);
        this.parkingLotService.allocateParkingSlotToCar("ABCDEDF", "Black");
        this.parkingLotService.allocateParkingSlotToCar(" ", "Pink");
        this.parkingLotService.allocateParkingSlotToCar("Hello", "White");
        assertEquals(String.format("Created a parking lot with %d slots\n" +
                "Allocated slot number: %d\n" +
                "Allocated slot number: %d\n" +
                "Sorry, parking lot is full\n", 2,1,2), outContent.toString());
    }

    @Test
    public void deallocateParkingSlot() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        this.parkingLotService.createParkingSlot(5);
        this.parkingLotService.allocateParkingSlotToCar("ABCDEDF", "Black");
        this.parkingLotService.allocateParkingSlotToCar(" ", "Pink");
        this.parkingLotService.deallocateParkingSlot(1);
        assertEquals(String.format("Created a parking lot with %d slots\n" +
                "Allocated slot number: %d\n" +
                "Allocated slot number: %d\n" +
                "Slot number %d is free\n", 5,1,2,1), outContent.toString());
    }

    @Test
    public void parkingStatus() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        this.parkingLotService.createParkingSlot(5);
        this.parkingLotService.allocateParkingSlotToCar("ABCDEDF", "Black");
        this.parkingLotService.allocateParkingSlotToCar(" ", "Pink");
        this.parkingLotService.allocateParkingSlotToCar("Hello", "White");
        this.parkingLotService.parkingStatus();
        assertEquals(String.format("Created a parking lot with %d slots\n" +
                "Allocated slot number: %d\n" +
                "Allocated slot number: %d\n" +
                "Allocated slot number: %d\n" +
                "Slot No.\tRegistration No\tColour\n" +
                "1\tABCDEDF\tBlack\n" +
                "2\t \tPink\n" +
                "3\tHello\tWhite\n", 5,1,2,3), outContent.toString());
    }

    @Test
    public void listRegistrationNumberOfCars() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        this.parkingLotService.createParkingSlot(5);
        this.parkingLotService.allocateParkingSlotToCar("ABCDEDF", "Black");
        this.parkingLotService.allocateParkingSlotToCar(" ", "Pink");
        this.parkingLotService.allocateParkingSlotToCar("Hello", "Black");
        this.parkingLotService.listRegistrationNumberOfCars("Black");
        assertEquals(String.format("Created a parking lot with %d slots\n" +
                "Allocated slot number: %d\n" +
                "Allocated slot number: %d\n" +
                "Allocated slot number: %d\n" +
                "ABCDEDF, Hello\n", 5,1,2,3), outContent.toString());
    }

    @Test
    public void listSlotNumberOfCars() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        this.parkingLotService.createParkingSlot(5);
        this.parkingLotService.allocateParkingSlotToCar("ABCDEDF", "Black");
        this.parkingLotService.allocateParkingSlotToCar(" ", "Black");
        this.parkingLotService.allocateParkingSlotToCar("Hello", "Black");
        this.parkingLotService.deallocateParkingSlot(2);
        this.parkingLotService.listSlotNumberOfCars("Black");
        assertEquals(String.format("Created a parking lot with %d slots\n" +
                "Allocated slot number: %d\n" +
                "Allocated slot number: %d\n" +
                "Allocated slot number: %d\n" +
                "Slot number %d is free\n" +
                "1, 3\n", 5,1,2,3,2), outContent.toString());
    }

    @Test
    public void listSlotNumberOfOfCar() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        this.parkingLotService.createParkingSlot(5);
        this.parkingLotService.allocateParkingSlotToCar("ABCDEDF", "Yellow");
        this.parkingLotService.allocateParkingSlotToCar("Hello", "Black");
        this.parkingLotService.slotNumberOfCar("ABCDEDF");
        this.parkingLotService.slotNumberOfCar(" ");
        assertEquals(String.format("Created a parking lot with %d slots\n" +
                "Allocated slot number: %d\n" +
                "Allocated slot number: %d\n" +
                "%d\n" +
                "Not found\n", 5,1,2,1), outContent.toString());
    }

}