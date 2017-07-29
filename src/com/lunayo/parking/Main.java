package com.lunayo.parking;

import com.lunayo.parking.application.CommandParserService;
import com.lunayo.parking.application.ParkingLotService;
import com.lunayo.parking.port.adapter.persistence.InMemoryCarRepository;
import com.lunayo.parking.port.adapter.persistence.InMemoryParkingSlotRepository;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Can be implemented using Dependency Injection
        CommandParserService cs = new CommandParserService(
                new ParkingLotService(new InMemoryCarRepository(), new InMemoryParkingSlotRepository()));
        if(args.length > 0) {
            cs.executeFileCommand(args[0]);
        } else {
            Scanner s = new Scanner(System.in);
            String line = s.nextLine();
            while(line != null) {
                cs.executeCommand(line);
                line = s.nextLine();
            }
        }
    }
}
