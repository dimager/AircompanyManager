package com.epam.jwd.controller.command.brigade;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.flight.AddFlightCommand;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.impl.BrigadeService;
import com.epam.jwd.service.impl.FlightService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeBrigadeArchiveStatusCommand implements Command {
    public static String FLIGHT_JSP = "/controller?command=SHOW_FLIGHT_PAGE";
    private static final Logger logger = LogManager.getLogger(AddFlightCommand.class);
    private static final Command INSTANCE = new ChangeBrigadeArchiveStatusCommand();
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
    private static final int RESULT_MESSAGE_CODE_ARCHIVED = 125;
    private static final int RESULT_MESSAGE_CODE_UNARCHIVED = 126;
    private static final int ERROR_CODE_NUMBER_FORMAT_EXECPTION = 128;

    ChangeBrigadeArchiveStatusCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        logger.debug("execute method");
        BrigadeService brigadeService = new BrigadeService();
        try {
            long brigadeId = Long.parseLong(requestContext.getParamFromJSP(Attributes.ARCHIVE_BRIGADE_ID_ATTRIBUTE));
            boolean isArchived = Boolean.parseBoolean(requestContext.getParamFromJSP(Attributes.IS_ARCHIVED_ID_ATTRIBUTE));
            brigadeService.changeArchiveStatus(brigadeId, isArchived);
            if (isArchived) {
                FLIGHT_JSP = "/controller?command=SHOW_BRIGADE_PAGE";
                requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE, RESULT_MESSAGE_CODE_ARCHIVED);
            } else {
                FLIGHT_JSP = "/controller?command=SHOW_ARCHIVED_BRIGADE_PAGE";
                requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE, RESULT_MESSAGE_CODE_UNARCHIVED);
            }
        } catch (NumberFormatException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, ERROR_CODE_NUMBER_FORMAT_EXECPTION);
        } catch (DAOException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
        }
        return CHANGE_FLIGHT_ARCHIVE_STATUS_COMMAND_CONTEXT;
    }
}
