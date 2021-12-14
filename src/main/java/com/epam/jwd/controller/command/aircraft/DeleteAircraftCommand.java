package com.epam.jwd.controller.command.aircraft;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.impl.AircraftService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class DeleteAircraftCommand implements Command {
    private static final Logger logger = LogManager.getLogger(DeleteAircraftCommand.class);
    private static final Command INSTANCE = new DeleteAircraftCommand();
    private static final String DELETE_AIRCRAFT_JSP = "/controller?command=SHOW_AIRCRAFT_PAGE";
    private static final int RESULT_MESSAGE_CODE = 102;
    private static final int PARSING_ERROR_CODE = 247;

    private static final ResponseContext DELETE_AIRCRAFT_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return DELETE_AIRCRAFT_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private DeleteAircraftCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        logger.debug("execute method");
        AircraftService aircraftService = new AircraftService();
        if (Objects.nonNull(requestContext.getParamFromJSP(Attributes.DELETE_AIRCRAFT_ID_ATTRIBUTE))) {
            try {
                aircraftService.deleteAircraftById(Integer.parseInt(requestContext.getParamFromJSP(Attributes.DELETE_AIRCRAFT_ID_ATTRIBUTE)));
                requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE, RESULT_MESSAGE_CODE);

            } catch (DAOException e) {
                logger.error(e);
                requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
            } catch (NumberFormatException e) {
                logger.error(e);
                requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, PARSING_ERROR_CODE);
            }
        }
        return DELETE_AIRCRAFT_CONTEXT;
    }
}
