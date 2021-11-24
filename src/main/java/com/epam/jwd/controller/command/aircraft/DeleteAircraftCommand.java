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
    private static final String RESULT_MESSAGE = "Successfully deleted";

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
        AircraftService aircraftService = new AircraftService();
        if (Objects.nonNull(requestContext.getParamFromJSP(Attributes.DELETE_AIRCRAFT_ID_ATTRIBUTE_NAME))) {
            try {
                aircraftService.deleteAircraftById(Integer.parseInt(requestContext.getParamFromJSP(Attributes.DELETE_AIRCRAFT_ID_ATTRIBUTE_NAME)));
                requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE_NAME, RESULT_MESSAGE);

            } catch (DAOException |  NumberFormatException e) {
                logger.error(e);
                requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE_NAME, e.getMessage());
            }
        }
        return DELETE_AIRCRAFT_CONTEXT;
    }
}
