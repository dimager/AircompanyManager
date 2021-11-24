package com.epam.jwd.controller.command.brigade;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.BrigadeDTO;
import com.epam.jwd.service.dto.UserDTO;
import com.epam.jwd.service.impl.BrigadeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteUserFromBrigadeCommand implements Command {
    private static final Logger logger = LogManager.getLogger(DeleteUserFromBrigadeCommand.class);
    private static final Command INSTANCE = new DeleteUserFromBrigadeCommand();
    private static final String ADD_BRIGADE_JSP = "/controller?command=SHOW_BRIGADE_WITH_USERS_PAGE";

    private static final ResponseContext ADD_BRIGADES_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return ADD_BRIGADE_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };
    private static final String RESULT_MESSAGE = "User was deleted from brigade.";

    private DeleteUserFromBrigadeCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        BrigadeService brigadeService = new BrigadeService();
        try {
            long userId = Long.parseLong(requestContext.getParamFromJSP("user_id"));
            long brigadeId = Long.parseLong(requestContext.getParamFromJSP("brigade_id"));
            requestContext.addAttributeToJSP("brigade_id", brigadeId);
            brigadeService.removeUserFromBrigade(userId, brigadeId);
            requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE_NAME,   RESULT_MESSAGE );
        } catch (NumberFormatException | DAOException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE_NAME, e.getMessage());
        }

        return ADD_BRIGADES_PAGE_CONTEXT;
    }
}
