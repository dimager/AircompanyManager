package com.epam.jwd.controller.command;

import com.epam.jwd.controller.command.aircraft.AddAircraftCommand;
import com.epam.jwd.controller.command.aircraft.DeleteAircraftCommand;
import com.epam.jwd.controller.command.aircraft.EditAircraftCommand;
import com.epam.jwd.controller.command.aircraft.ShowAddEditAircraftPageCommand;
import com.epam.jwd.controller.command.aircraft.ShowAircraftPageCommand;
import com.epam.jwd.controller.command.airport.AddAirportCommand;
import com.epam.jwd.controller.command.airport.DeleteAirportCommand;
import com.epam.jwd.controller.command.airport.EditAirportCommand;
import com.epam.jwd.controller.command.airport.ShowAirportPage;
import com.epam.jwd.controller.command.authentication.LoginCommand;
import com.epam.jwd.controller.command.authentication.LogoutCommand;
import com.epam.jwd.controller.command.authentication.ShowLoginPageCommand;
import com.epam.jwd.controller.command.authentication.ShowSignupCommand;
import com.epam.jwd.controller.command.authentication.SignupCommand;
import com.epam.jwd.controller.command.brigade.AddBrigadeCommand;
import com.epam.jwd.controller.command.brigade.AddUserToBrigadeCommand;
import com.epam.jwd.controller.command.brigade.DeleteBrigadeCommand;
import com.epam.jwd.controller.command.brigade.DeleteUserFromBrigadeCommand;
import com.epam.jwd.controller.command.brigade.EditBrigadeCommand;
import com.epam.jwd.controller.command.brigade.ShowAddEditBrigadePageCommand;
import com.epam.jwd.controller.command.brigade.ShowBrigadePageCommand;
import com.epam.jwd.controller.command.brigade.ShowBrigadeWithUsersPageCommand;
import com.epam.jwd.controller.command.flight.AddFlightCommand;
import com.epam.jwd.controller.command.flight.ChangeFlightBrigadeCommand;
import com.epam.jwd.controller.command.flight.DeleteFlightCommand;
import com.epam.jwd.controller.command.flight.EditFlightCommand;
import com.epam.jwd.controller.command.flight.ShowAddEditFlightCommand;
import com.epam.jwd.controller.command.flight.ShowFlightCommand;
import com.epam.jwd.controller.command.locale.SetLocaleCommand;
import com.epam.jwd.controller.command.user.ChangePasswordCommand;
import com.epam.jwd.controller.command.user.ChangeRoleCommand;
import com.epam.jwd.controller.command.user.ShowAllUsersCommand;
import com.epam.jwd.controller.command.user.ShowUserBrigadesCommand;
import com.epam.jwd.controller.command.user.ShowUserFlightsCommand;
import com.epam.jwd.controller.command.user.ShowUserPageCommand;
import com.epam.jwd.dao.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Commands {
    ADD_AIRCRAFT(AddAircraftCommand.getInstance(), Role.ADMIN),
    ADD_AIRPORT(AddAirportCommand.getInstance(), Role.ADMIN),
    ADD_BRIGADE(AddBrigadeCommand.getInstance(), Role.MANAGER),
    ADD_USER_TO_BRIGADE_PAGE(AddUserToBrigadeCommand.getInstance(), Role.MANAGER),
    CHANGE_ROLE(ChangeRoleCommand.getInstance(), Role.ADMIN),
    DEFAULT(DefaultCommand.getInstance(), Role.ADMIN),
    DELETE_AIRCRAFT(DeleteAircraftCommand.getInstance(), Role.ADMIN),
    DELETE_BRIGADE(DeleteBrigadeCommand.getInstance(), Role.MANAGER),
    DELETE_AIRPORT(DeleteAirportCommand.getInstance(), Role.ADMIN),
    DELETE_USER_FROM_BRIGADE(DeleteUserFromBrigadeCommand.getInstance(), Role.MANAGER),
    EDIT_AIRCRAFT(EditAircraftCommand.getInstance(), Role.ADMIN),
    EDIT_BRIGADE(EditBrigadeCommand.getInstance(), Role.MANAGER),
    EDIT_AIRPORT(EditAirportCommand.getInstance(), Role.ADMIN),
    LOGIN(LoginCommand.getInstance()),
    SHOW_ADD_AIRCRAFT_PAGE(ShowAddEditAircraftPageCommand.getInstance(), Role.ADMIN),
    SHOW_ADD_BRIGADE_PAGE(ShowAddEditBrigadePageCommand.getInstance(), Role.MANAGER),
    SHOW_AIRCRAFT_PAGE(ShowAircraftPageCommand.getInstance(), Role.ADMIN),
    SHOW_ALL_USERS(ShowAllUsersCommand.getInstance(), Role.ADMIN, Role.MANAGER),
    SHOW_BRIGADE_PAGE(ShowBrigadePageCommand.getInstance(), Role.MANAGER),
    SHOW_BRIGADE_WITH_USERS_PAGE(ShowBrigadeWithUsersPageCommand.getInstance(), Role.MANAGER),
    SHOW_EDIT_AIRCRAFT_PAGE(ShowAddEditAircraftPageCommand.getInstance(), Role.ADMIN),
    SHOW_EDIT_BRIGADE_PAGE(ShowAddEditBrigadePageCommand.getInstance(), Role.MANAGER),
    SHOW_FLIGHT_PAGE(ShowFlightCommand.getInstance(), Role.ADMIN, Role.MANAGER, Role.FLIGHT_ATTENDANT, Role.NAVIGATOR, Role.PILOT, Role.RADIO_ENGINEER),
    SHOW_ADD_FLIGHT_PAGE(ShowAddEditFlightCommand.getInstance(), Role.ADMIN),
    SHOW_EDIT_FLIGHT_PAGE(ShowAddEditFlightCommand.getInstance(), Role.ADMIN),
    SHOW_LOGIN_PAGE(ShowLoginPageCommand.getInstance()),
    SHOW_SIGNUP_PAGE(ShowSignupCommand.getInstance()),
    SIGNUP(SignupCommand.getInstance()),
    ADD_FLIGHT(AddFlightCommand.getInstance(), Role.ADMIN),
    EDIT_FLIGHT(EditFlightCommand.getInstance(), Role.ADMIN),
    DELETE_FLIGHT(DeleteFlightCommand.getInstance(), Role.ADMIN),
    CHANGE_BRIGADE(ChangeFlightBrigadeCommand.getInstance(), Role.MANAGER),
    SHOW_USER_PAGE(ShowUserPageCommand.getInstance()),
    SHOW_USER_BRIGADES_PAGE(ShowUserBrigadesCommand.getInstance(),Role.RADIO_ENGINEER, Role.PILOT, Role.FLIGHT_ATTENDANT, Role.NAVIGATOR),
    SHOW_USER_BRIGADES_PAGE_FOR_MANAGER(ShowUserBrigadesCommand.getInstance(),Role.MANAGER),
    CHANGE_PASSWORD(ChangePasswordCommand.getInstance()),
    SHOW_AIRPORT_PAGE(ShowAirportPage.getInstance(), Role.ADMIN),
    SHOW_USER_FLIGHTS_PAGE(ShowUserFlightsCommand.getInstance(), Role.RADIO_ENGINEER, Role.PILOT, Role.FLIGHT_ATTENDANT, Role.NAVIGATOR),
    SHOW_USER_FLIGHTS_PAGE_FOR_MANAGER(ShowUserFlightsCommand.getInstance(), Role.MANAGER),
    LOGOUT(LogoutCommand.getInstance()),
    SET_LOCALE(SetLocaleCommand.getInstance());

    private static final Logger logger = LogManager.getLogger(Commands.class);
    private final List<Role> permitRoles = new ArrayList<>();
    private final Command command;

    Commands(Command command, Role... permitRoles) {

        this.permitRoles.addAll(Arrays.asList(permitRoles));
        this.command = command;
    }

    static List<Role> getPermitRoles(String commandName) {
        logger.debug("getPermitRoles method");
        return Arrays.stream(Commands.values())
                .filter(command -> command.name().equalsIgnoreCase(commandName))
                .map(commands -> commands.permitRoles)
                .findFirst()
                .orElse(null);
    }

    static Command getCommand(String name) {
        logger.debug("getCommand method");
        return Arrays.stream(Commands.values())
                .filter(command -> command.name().equalsIgnoreCase(name))
                .map(command -> command.command)
                .findFirst()
                .orElse(DefaultCommand.getInstance());
    }

    static Commands getCommandName(String name) {
        logger.debug("getCommandName method");
        return Arrays.stream(Commands.values())
                .filter(commands -> commands.name().equals(name))
                .findFirst()
                .orElse(null);
    }
}

