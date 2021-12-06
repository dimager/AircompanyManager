package com.epam.jwd.controller.command.flight;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.entity.Flight;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.impl.FlightService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.format.DateTimeParseException;

public class DeleteFlightCommand implements Command {
    public static final String FLIGHT_JSP = "/controller?command=SHOW_FLIGHT_PAGE";
    private static final Logger logger = LogManager.getLogger(AddFlightCommand.class);
    private static final Command INSTANCE = new DeleteFlightCommand();
    private static final int RESULT_MESSAGE_CODE = 113;

    private static final ResponseContext DELETE_FLIGHT_COMMAND_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return FLIGHT_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    DeleteFlightCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        logger.debug("execute method");
        FlightService flightService = new FlightService();
        Flight flight = new Flight();
        try {
            long id = Long.parseLong(requestContext.getParamFromJSP(Attributes.DELETE_FLIGHT_ID_ATTRIBUTE));
            flightService.deleteFlightById(id);
            requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE, RESULT_MESSAGE_CODE);
        } catch (DateTimeParseException | DAOException  | NumberFormatException | NullPointerException e) {
            e.printStackTrace();
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e);
            requestContext.addAttributeToJSP(Attributes.AIRCRAFT_DTO_ATTRIBUTE, flight);
        }
        return DELETE_FLIGHT_COMMAND_CONTEXT;
    }
}
