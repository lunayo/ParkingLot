package com.lunayo.parking.domain.model.command;

/**
 * Created by Lunayo on 29/07/2017.
 *
 */
public enum Command {
    CREATE_PARKING_LOT ("create_parking_lot"),
    PARK ("park"),
    LEAVE ("leave"),
    STATUS ("status"),
    REGISTRATION_NO_WITH_COLOUR ("registration_numbers_for_cars_with_colour"),
    SLOT_WITH_COLOUR ("slot_numbers_for_cars_with_colour"),
    SLOT_WITH_REGISTRATION_NO ("slot_number_for_registration_number");

    private final String commandString;

    Command(String commandString) {
        this.commandString = commandString;
    }

    public static Command fromString(String text) {
        for (Command b : Command.values()) {
            if (b.commandString.equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
