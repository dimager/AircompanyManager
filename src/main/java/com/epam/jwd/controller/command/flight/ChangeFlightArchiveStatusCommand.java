package com.epam.jwd.controller.command.flight;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.FlightDTO;
import com.epam.jwd.service.impl.BrigadeService;
import com.epam.jwd.service.impl.FlightService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class ChangeFlightArchiveStatusCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddFlightCommand.class);
    private static final Command INSTANCE = new ChangeFlightArchiveStatusCommand();
    private static final String RESULT_MESSAGE_CODE_ARCHIVED = "123";
    private static final String PARSING_EXECPTION = "300";
    private static final String ERROR_CODE = "12";
    public static String FLIGHT_JSP = "/controller?command=SHOW_FLIGHT_PAGE";
    private static final ResponseContext CHANGE_FLIGHT_ARCHIVE_STATUS_COMMAND_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return FLIGHT_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    ChangeFlightArchiveStatusCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        logger.debug("execute method");
        FlightService flightService = new FlightService();
        BrigadeService brigadeService = new BrigadeService();
        try {
            long flightId = Long.parseLong(requestContext.getParamFromJSP(Attributes.ARCHIVE_FLIGHT_ID_ATTRIBUTE));
            boolean isArchived = Boolean.parseBoolean(requestContext.getParamFromJSP(Attributes.IS_ARCHIVED_ID_ATTRIBUTE));
            FlightDTO flightDTO = flightService.findFlightById(flightId);
            if (isArchived) {
                if (Objects.nonNull(flightDTO.getBrigadeDTO())) {
                    brigadeService.changeArchiveStatus(flightDTO.getBrigadeDTO().getBrigadeId(), isArchived);
                    flightService.changeArchiveStatus(flightId, isArchived);
                    requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE, RESULT_MESSAGE_CODE_ARCHIVED);
                } else {
                    requestContext.addAttributeToJSP(Attributes.COMMAND_ONEERROR_ATTRIBUTE, ERROR_CODE);
                }
            }
        } catch (NumberFormatException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, PARSING_EXECPTION );
        } catch (DAOException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
        }
        return CHANGE_FLIGHT_ARCHIVE_STATUS_COMMAND_CONTEXT;
    }
}
