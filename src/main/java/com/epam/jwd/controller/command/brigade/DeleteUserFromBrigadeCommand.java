package com.epam.jwd.controller.command.brigade;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.BrigadeDTO;
import com.epam.jwd.service.impl.BrigadeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteUserFromBrigadeCommand implements Command {
    private static final Logger logger = LogManager.getLogger(DeleteUserFromBrigadeCommand.class);
    private static final Command INSTANCE = new DeleteUserFromBrigadeCommand();
    private static final String SHOW_BRIGADE_WITH_USERS_PAGE = "/controller?command=SHOW_BRIGADE_WITH_USERS_PAGE";
    private static final int RESULT_MESSAGE_CODE = 109;
    private static final int ERROR_CODE = 130;

    private static final ResponseContext DELETE_USER_FROM_BRIGADES_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return SHOW_BRIGADE_WITH_USERS_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private DeleteUserFromBrigadeCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        logger.debug("execute method");
        BrigadeService brigadeService = new BrigadeService();
        try {
            long userId = Long.parseLong(requestContext.getParamFromJSP(Attributes.USER_ID_ATTRIBUTE));
            long brigadeId = Long.parseLong(requestContext.getParamFromJSP(Attributes.BRIGADE_ID_ATTRIBUTE));
            requestContext.addAttributeToJSP(Attributes.BRIGADE_ID_ATTRIBUTE, brigadeId);
            BrigadeDTO brigadeDTO = brigadeService.findById(brigadeId);
            if (brigadeDTO.getIsArchived()){
                requestContext.addAttributeToJSP(Attributes.COMMAND_ONEERROR_ATTRIBUTE,  ERROR_CODE);
            }else {
                brigadeService.removeUserFromBrigade(userId, brigadeId);
                requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE,  RESULT_MESSAGE_CODE);
            }
        } catch (NumberFormatException | DAOException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
        }

        return DELETE_USER_FROM_BRIGADES_PAGE_CONTEXT;
    }
}
