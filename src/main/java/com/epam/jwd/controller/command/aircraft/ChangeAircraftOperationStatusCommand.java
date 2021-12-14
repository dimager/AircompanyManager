package com.epam.jwd.controller.command.aircraft;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.impl.AircraftService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeAircraftOperationStatusCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeAircraftOperationStatusCommand.class);
    private static final Command INSTANCE = new ChangeAircraftOperationStatusCommand();
    private static final String AIRCRAFT_JSP = "/controller?command=SHOW_AIRCRAFT_PAGE";
    private static final int RESULT_MESSAGE_CODE_IN_OPERATION = 129;
    private static final int RESULT_MESSAGE_CODE_NOT_IN_OPERATION = 130;
    private static final int PARSING_ERROR_CODE = 247;

    private static final ResponseContext CHANGE_AIRCRAFT_OPERATION_STATUS_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return AIRCRAFT_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private ChangeAircraftOperationStatusCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        logger.debug("execute method");
        AircraftService aircraftService = new AircraftService();

            try {
                int aircraftId = Integer.parseInt(requestContext.getParamFromJSP(Attributes.AIRCRAFT_ID_ATTRIBUTE));
                boolean inOperation = Boolean.parseBoolean(requestContext.getParamFromJSP(Attributes.AIRCRAFT_IN_OPERATION_ATTRIBUTE));
                aircraftService.changeInOperationStatus(aircraftId,inOperation);
                if (inOperation) {
                    requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE, RESULT_MESSAGE_CODE_IN_OPERATION);
                }
                else {
                    requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE, RESULT_MESSAGE_CODE_NOT_IN_OPERATION);
                }
            } catch (DAOException e) {
                logger.error(e);
                requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
            } catch (NumberFormatException e) {
                logger.error(e);
                requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, PARSING_ERROR_CODE);
            }
        return CHANGE_AIRCRAFT_OPERATION_STATUS_CONTEXT;
    }
}
