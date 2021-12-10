package com.epam.jwd.controller.command.flight;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.entity.Brigade;
import com.epam.jwd.dao.entity.Flight;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.exception.ValidatorException;
import com.epam.jwd.service.impl.BrigadeService;
import com.epam.jwd.service.impl.FlightService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.format.DateTimeParseException;

public class ChangeFlightBrigadeCommand implements Command {
    public static final String FLIGHT_JSP = "/controller?command=SHOW_FLIGHT_PAGE";
    private static final Logger logger = LogManager.getLogger(AddFlightCommand.class);
    private static final Command INSTANCE = new ChangeFlightBrigadeCommand();
    private static final int RESULT_MESSAGE_CODE = 112;
    private static final int ERROR_CODE_FLIGHT = 133;
    private static final int ERROR_CODE_BRIGADE = 135;


    private static final ResponseContext CHANGE_FLIGHT_BRIGADE_COMMAND_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return FLIGHT_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    ChangeFlightBrigadeCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        logger.debug("execute method");
        FlightService flightService = new FlightService();
        BrigadeService brigadeService =new BrigadeService();
        try {
            long brigadeId = Long.parseLong(requestContext.getParamFromJSP(Attributes.NEW_BRIGADE_ID_ATTRIBUTE));
            long flightId = Long.parseLong(requestContext.getParamFromJSP(Attributes.EDIT_FLIGHT_ID_ATTRIBUTE));

            if (flightService.findFlightById(flightId).getIsArchived()) {
                requestContext.addAttributeToJSP(Attributes.COMMAND_ONEERROR_ATTRIBUTE,  ERROR_CODE_FLIGHT);
            }
            else if (brigadeService.findById(brigadeId).getIsArchived()){
                requestContext.addAttributeToJSP(Attributes.COMMAND_ONEERROR_ATTRIBUTE,  ERROR_CODE_BRIGADE);
            }
            else {
                flightService.updateFlightBrigade(flightId,brigadeId);
                requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE, RESULT_MESSAGE_CODE);
            }

        } catch (DAOException | NumberFormatException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
        }
        return CHANGE_FLIGHT_BRIGADE_COMMAND_CONTEXT;
    }
}
