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

public class AddUserToBrigadeCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddUserToBrigadeCommand.class);
    private static final Command INSTANCE = new AddUserToBrigadeCommand();
    private static  String ADD_USER_TO_BRIGADE_JSP = "/controller?command=SHOW_BRIGADE_WITH_USERS_PAGE";
    private static final int ERROR_CODE = 129;
    private static final int RESULT_MESSAGE_CODE = 107;
    private static final int PARSING_ERROR_CODE = 247;

    private static final ResponseContext ADD_BRIGADES_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return ADD_USER_TO_BRIGADE_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private AddUserToBrigadeCommand() {
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
            long brigadeId = Long.parseLong(requestContext.getParamFromJSP("brigade_id"));
            BrigadeDTO brigadeDTO = brigadeService.findById(brigadeId);
            if (brigadeDTO.getIsArchived()){
                requestContext.addAttributeToJSP(Attributes.COMMAND_ONEERROR_ATTRIBUTE,  ERROR_CODE);
            }else {
                brigadeService.addUserToBrigade(userId, brigadeId);
                requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE,  RESULT_MESSAGE_CODE);
            }
            ADD_USER_TO_BRIGADE_JSP="/controller?command=SHOW_BRIGADE_WITH_USERS_PAGE&brigade_id="+brigadeId;
        } catch (NumberFormatException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE,PARSING_ERROR_CODE );
        } catch (DAOException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
        }

        return ADD_BRIGADES_PAGE_CONTEXT;
    }
}
