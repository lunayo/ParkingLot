package com.lunayo.parking.application;

import com.lunayo.parking.domain.model.command.Command;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Collection;
import java.util.List;

/**
 * Created by Lunayo on 29/07/2017.
 *
 */
public class CommandParserService {

    private ParkingLotService parkingLotService;

    public CommandParserService(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    public void executeFileCommand(String command) {
        if(command.contains(".txt")) {
            try {
                List<String> commands =
                        Files.readAllLines(new File("input.txt").toPath(), Charset.defaultCharset());
                this.executeCommand(commands);
            } catch (IOException e) {
                throw new IllegalArgumentException("File was not found.");
            }
        }
    }

    private void executeCommand(Collection<String> commands) {
        for(String command: commands) {
            this.executeCommand(command);
        }
    }

    public void executeCommand(String command) {
        String[] args = command.split(" ");
        if(args.length < 1) {
            throw new IllegalArgumentException("Command is incorrect.");
        }
        switch (Command.fromString(args[0])) {
            case CREATE_PARKING_LOT:
                if(args.length < 2) {
                    throw new IllegalArgumentException("Missing arguments.");
                }
                this.parkingLotService.createParkingSlot(Integer.parseInt(args[1]));
                break;
            case PARK:
                if(args.length < 3) {
                    throw new IllegalArgumentException("Missing arguments.");
                }
                this.parkingLotService.allocateParkingSlotToCar(args[1], args[2]);
                break;
            case LEAVE:
                if(args.length < 2) {
                    throw new IllegalArgumentException("Missing arguments.");
                }
                this.parkingLotService.deallocateParkingSlot(Integer.parseInt(args[1]));
                break;
            case STATUS:
                this.parkingLotService.parkingStatus();
                break;
            case REGISTRATION_NO_WITH_COLOUR:
                if(args.length < 2) {
                    throw new IllegalArgumentException("Missing arguments.");
                }
                this.parkingLotService.listRegistrationNumberOfCars(args[1]);
                break;
            case SLOT_WITH_COLOUR:
                if(args.length < 2) {
                    throw new IllegalArgumentException("Missing arguments.");
                }
                this.parkingLotService.listSlotNumberOfCars(args[1]);
                break;
            case SLOT_WITH_REGISTRATION_NO:
                if(args.length < 2) {
                    throw new IllegalArgumentException("Missing arguments.");
                }
                this.parkingLotService.listSlotNumberOfOfCar(args[1]);
                break;
        }

    }
}
