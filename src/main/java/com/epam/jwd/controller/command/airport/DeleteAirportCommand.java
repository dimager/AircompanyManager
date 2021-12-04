package com.epam.jwd.controller.command.airport;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.impl.AircraftService;
import com.epam.jwd.service.impl.AirportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteAirportCommand implements Command {
    private static final Logger logger = LogManager.getLogger(DeleteAirportCommand.class);
    private static final Command INSTANCE = new DeleteAirportCommand();
    private static final String DELETE_AIRPORT_JSP = "/controller?command=SHOW_AIRPORT_PAGE";
    private static final int RESULT_MESSAGE_CODE = 119;

    private static final ResponseContext DELETE_AIRCRAFT_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return DELETE_AIRPORT_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private DeleteAirportCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        AirportService airportService = new AirportService();
            try {
                airportService.deleteAirport(Integer.parseInt(requestContext.getParamFromJSP(Attributes.DELETE_AIRPORT_ID_ATTRIBUTE_NAME)));
                requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE_NAME, RESULT_MESSAGE_CODE);
            } catch (DAOException |  NumberFormatException e) {
                logger.error(e);
                requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE_NAME, e.getMessage());
            }
        return DELETE_AIRCRAFT_CONTEXT;
    }
}
