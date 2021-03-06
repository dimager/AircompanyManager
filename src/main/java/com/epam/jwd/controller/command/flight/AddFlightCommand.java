package com.epam.jwd.controller.command.flight;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.entity.Flight;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.exception.ValidatorException;
import com.epam.jwd.service.impl.FlightService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddFlightCommand implements Command {
    public static final String ADD_EDIT_JSP = "controller?command=SHOW_FLIGHT_PAGE";
    private static final Logger logger = LogManager.getLogger(AddFlightCommand.class);
    private static final Command INSTANCE = new AddFlightCommand();
    private static final int RESULT_MESSAGE_CODE = 111;
    private static final int PARSING_ERROR_CODE = 247;
    private static final String UTC = " UTC";
    private static final String DATE_TIME_PATTERN = "MM/dd/yyyy h:mm a z";
    private static final ResponseContext ADD_EDIT_FLIGHT_COMMAND_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return ADD_EDIT_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    AddFlightCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        logger.debug("execute method");
        FlightService flightService = new FlightService();

        try {
            String flightDateTime = requestContext.getParamFromJSP(Attributes.SELECTED_DATE_TIME) + UTC;
            Timestamp timestamp = Timestamp.from(ZonedDateTime.parse(flightDateTime, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)).toInstant());
            int aircraftId = Integer.parseInt(requestContext.getParamFromJSP(Attributes.SELECTED_AIRCRAFT_FIELD_NAME));
            int destinationAirportId = Integer.parseInt(requestContext.getParamFromJSP(Attributes.SELECTED_DESTINATION_AIRPORT_FIELD_NAME));
            int departureAirportId = Integer.parseInt(requestContext.getParamFromJSP(Attributes.SELECTED_DEPARTURE_AIRPORT_FIELD_NAME));
            String flightCallsign = requestContext.getParamFromJSP(Attributes.CALLSIGN_INPUT_FIELD);

            Flight flight = new Flight();
            flight.setFlightCallsign(flightCallsign);
            flight.setDepartureDateTime(timestamp);
            flight.setFlightAircraftId(aircraftId);
            flight.setDepartureAirportId(departureAirportId);
            flight.setDestinationAirportId(destinationAirportId);
            flightService.saveFlight(flight);
            requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE, RESULT_MESSAGE_CODE);

        } catch (DAOException | ValidatorException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
        } catch (DateTimeParseException | NumberFormatException | NullPointerException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, PARSING_ERROR_CODE);
        }

        return ADD_EDIT_FLIGHT_COMMAND_CONTEXT;
    }
}
