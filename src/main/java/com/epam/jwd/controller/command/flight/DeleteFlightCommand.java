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

public class DeleteFlightCommand implements Command {
    public static final String FLIGHT_JSP = "/controller?command=SHOW_FLIGHT_PAGE";
    private static final Logger logger = LogManager.getLogger(AddFlightCommand.class);
    private static final int PARSING_ERROR_CODE = 247;
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
    private static final String ERROR_CODE = "11";

    DeleteFlightCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        logger.debug("execute method");
        FlightService flightService = new FlightService();
        try {
            long id = Long.parseLong(requestContext.getParamFromJSP(Attributes.DELETE_FLIGHT_ID_ATTRIBUTE));
            FlightDTO flightDTO = flightService.findFlightById(id);
            if (!flightDTO.getIsArchived()) {
                flightService.deleteFlightById(id);
                requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE, RESULT_MESSAGE_CODE);
            }
            else {
                requestContext.addAttributeToJSP(Attributes.COMMAND_ONEERROR_ATTRIBUTE,  ERROR_CODE);
            }
        } catch (DAOException e) {
            e.printStackTrace();
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, PARSING_ERROR_CODE);
        }
        return DELETE_FLIGHT_COMMAND_CONTEXT;
    }
}
