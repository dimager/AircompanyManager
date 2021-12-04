package com.epam.jwd.controller.command.flight;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.entity.Flight;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.FlightDTO;
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
        FlightService flightService = new FlightService();
        Flight flight = new Flight();

        try {

            //long brigadeId = Long.parseLong(requestContext.getParamFromJSP(Attributes.SELECTED_BRIGADE_FIELD_NAME));

            int departureAirportId = Integer.parseInt(requestContext.getParamFromJSP(Attributes.SELECTED_DEPARTURE_AIRPORT_FIELD_NAME));
            int destinationAirportId = Integer.parseInt(requestContext.getParamFromJSP(Attributes.SELECTED_DESTINATION_AIRPORT_FIELD_NAME));
            int aircraftId = Integer.parseInt(requestContext.getParamFromJSP(Attributes.SELECTED_AIRCRAFT_FIELD_NAME));
            String flightDate = requestContext.getParamFromJSP(Attributes.SELECTED_DATE_TIME) + " UTC";
            flight.setDepartureDateTime(Timestamp.from(ZonedDateTime.parse(flightDate, DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm a z")).toInstant()));
            flight.setFlightCallsign(requestContext.getParamFromJSP(Attributes.CALLSIGN_INPUT_FIELD).trim());
           // flight.setBrigadeId(0);
            flight.setFlightAircraftId(aircraftId);
            flight.setDepartureAirportId(departureAirportId);
            flight.setDestinationAirportId(destinationAirportId);
            System.out.println(flight);
            flightService.saveFlight(flight);
            requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE_NAME,  flight.getFlightCallsign() + RESULT_MESSAGE_CODE);

        } catch (DateTimeParseException | DAOException | ValidatorException | NumberFormatException | NullPointerException e  ) {
            e.printStackTrace();
            logger.error(e.getMessage());
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE_NAME, e.getMessage());
            requestContext.addAttributeToJSP(Attributes.AIRCRAFT_DTO_ATTRIBUTE_NAME, flight);
        }

        return ADD_EDIT_FLIGHT_COMMAND_CONTEXT;
    }
}
