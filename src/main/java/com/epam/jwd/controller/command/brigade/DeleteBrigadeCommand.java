package com.epam.jwd.controller.command.brigade;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.impl.BrigadeService;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteBrigadeCommand implements Command {
    private static final Logger logger = LogManager.getLogger(DeleteBrigadeCommand.class);
    private static final Command INSTANCE = new DeleteBrigadeCommand();
    private static final String DELETE_BRIGADE_JSP = "/controller?command=SHOW_BRIGADE_PAGE";
    private static final String DELETE_AIRCRAFT_ID_ATTRIBUTE_NAME = "delete_brigade_id";
    private static final String RESULT_MESSAGE = "Brigade is deleted";

    private static final ResponseContext DELETE_BRIGADES_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return DELETE_BRIGADE_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private DeleteBrigadeCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        BrigadeService brigadeService = new BrigadeService();
        try {
            long id = Long.parseLong(requestContext.getParamFromJSP(DELETE_AIRCRAFT_ID_ATTRIBUTE_NAME));
            brigadeService.deleteBrigade(id);
            requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE_NAME,  RESULT_MESSAGE);
        } catch (DAOException | NumberFormatException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE_NAME, e.getMessage());

        }
        return DELETE_BRIGADES_PAGE_CONTEXT;
    }
}
