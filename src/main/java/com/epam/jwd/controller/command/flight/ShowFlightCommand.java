package com.epam.jwd.controller.command.flight;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.FlightDTO;
import com.epam.jwd.service.impl.FlightService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ShowFlightCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowFlightCommand.class);
    public static final String FLIGHT_JSP = "/WEB-INF/jsp/flights.jsp";
    private static final Command INSTANCE = new ShowFlightCommand();
    private static final ResponseContext SHOW_FLIGHTS_COMMAND_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return FLIGHT_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    ShowFlightCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        FlightService flightService = new FlightService();
        try {
            List<FlightDTO> flightDTOS = flightService.findAllFlights();
            requestContext.addAttributeToJSP("flightDTOS", flightDTOS);
        } catch (DAOException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE_NAME, e.getMessage());
        }

        return SHOW_FLIGHTS_COMMAND_CONTEXT;
    }


}
