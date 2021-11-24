package com.epam.jwd.controller.command.brigade;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.BrigadeDTO;
import com.epam.jwd.service.impl.BrigadeService;
import com.epam.jwd.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowAddUserToBrigadePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowAddUserToBrigadePageCommand.class);
    private static final Command INSTANCE = new ShowAddUserToBrigadePageCommand();
    private static final String ADD_EDIT_JSP = "/WEB-INF/jsp/add_user_to_brigade.jsp";
    private static final ResponseContext SHOW_ADD_USER_TO_BRIGADE_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return ADD_EDIT_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private ShowAddUserToBrigadePageCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        BrigadeService brigadeService = new BrigadeService();
        UserService userService = new UserService();

        try {
            BrigadeDTO brigadeDTO = brigadeService.findById(Long.parseLong(requestContext.getParamFromJSP("brigade_id")));
            requestContext.addAttributeToJSP("brigadeDTO", brigadeDTO);
            requestContext.addAttributeToJSP("userDTOList", userService.findFreeWorkers(brigadeDTO.getBrigadeId()));
        } catch (DAOException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE_NAME, e.getMessage());
        }
        return SHOW_ADD_USER_TO_BRIGADE_PAGE_CONTEXT;
    }
}
