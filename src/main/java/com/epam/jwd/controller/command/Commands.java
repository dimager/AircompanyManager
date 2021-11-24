package com.epam.jwd.controller.command;

import com.epam.jwd.controller.command.aircraft.AddAircraftCommand;
import com.epam.jwd.controller.command.aircraft.DeleteAircraftCommand;
import com.epam.jwd.controller.command.aircraft.EditAircraftCommand;
import com.epam.jwd.controller.command.aircraft.ShowAddEditAircraftPageCommand;
import com.epam.jwd.controller.command.aircraft.ShowAircraftPageCommand;
import com.epam.jwd.controller.command.auth.LoginCommand;
import com.epam.jwd.controller.command.auth.ShowLoginPageCommand;
import com.epam.jwd.controller.command.auth.ShowSignupCommand;
import com.epam.jwd.controller.command.auth.SignupCommand;
import com.epam.jwd.controller.command.brigade.AddBrigadeCommand;
import com.epam.jwd.controller.command.brigade.AddUserToBrigadeCommand;
import com.epam.jwd.controller.command.brigade.DeleteBrigadeCommand;
import com.epam.jwd.controller.command.brigade.DeleteUserFromBrigadeCommand;
import com.epam.jwd.controller.command.brigade.EditBrigadeCommand;
import com.epam.jwd.controller.command.brigade.ShowAddEditBrigadePageCommand;
import com.epam.jwd.controller.command.brigade.ShowAddUserToBrigadePageCommand;
import com.epam.jwd.controller.command.brigade.ShowBrigadePageCommand;
import com.epam.jwd.controller.command.brigade.ShowBrigadeWithUsersPageCommand;
import com.epam.jwd.controller.command.user.ChangeRoleCommand;
import com.epam.jwd.controller.command.user.ShowAllUsersCommand;
import com.epam.jwd.controller.command.flight.ShowFlightCommand;

import java.util.Arrays;

public enum Commands {
    ADD_AIRCRAFT(AddAircraftCommand.getInstance()),
    ADD_BRIGADE(AddBrigadeCommand.getInstance()),
    ADD_USER_TO_BRIGADE_PAGE(AddUserToBrigadeCommand.getInstance()),
    CHANGE_ROLE(ChangeRoleCommand.getInstance()),
    DEFAULT(DefaultCommand.getInstance()),
    DELETE_AIRCRAFT(DeleteAircraftCommand.getInstance()),
    DELETE_BRIGADE(DeleteBrigadeCommand.getInstance()),
    DELETE_USER_FROM_BRIGADE(DeleteUserFromBrigadeCommand.getInstance()),
    EDIT_AIRCRAFT(EditAircraftCommand.getInstance()),
    EDIT_BRIGADE(EditBrigadeCommand.getInstance()),
    LOGIN(LoginCommand.getInstance()),
    SHOW_ADD_AIRCRAFT_PAGE(ShowAddEditAircraftPageCommand.getInstance()),
    SHOW_ADD_BRIGADE_PAGE(ShowAddEditBrigadePageCommand.getInstance()),
    SHOW_ADD_USER_TO_BRIGADE_PAGE(ShowAddUserToBrigadePageCommand.getInstance()),
    SHOW_AIRCRAFT_PAGE(ShowAircraftPageCommand.getInstance()),
    SHOW_ALL_USERS(ShowAllUsersCommand.getInstance()),
    SHOW_BRIGADE_PAGE(ShowBrigadePageCommand.getInstance()),
    SHOW_BRIGADE_WITH_USERS_PAGE(ShowBrigadeWithUsersPageCommand.getInstance()),
    SHOW_EDIT_AIRCRAFT_PAGE(ShowAddEditAircraftPageCommand.getInstance()),
    SHOW_EDIT_BRIGADE_PAGE(ShowAddEditBrigadePageCommand.getInstance()),
    SHOW_FLIGHT(ShowFlightCommand.getInstance()),
    SHOW_LOGIN_PAGE(ShowLoginPageCommand.getInstance()),
    SHOW_SIGNUP_PAGE(ShowSignupCommand.getInstance()),
    SIGNUP(SignupCommand.getInstance());


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

