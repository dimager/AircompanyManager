package com.epam.jwd.controller.command;

import java.util.Arrays;

public enum Commands {
    DEFAULT(1),
    SHOW_FLIGHT(2);

    private int id;

    Commands(int id) {
        this.id = id;
    }

    private Command command;

    Commands(Command command) {
        this.command = command;
    }

    static Command getCommand(String name) {

        return Arrays.stream(Commands.values())
                .filter(command -> command.name().equalsIgnoreCase(name))
                .map(command -> command.command)
                .findFirst()
                .orElse(DefaultCommand.getInstance());
    }
}
