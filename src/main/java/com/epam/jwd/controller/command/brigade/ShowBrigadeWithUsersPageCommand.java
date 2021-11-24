package com.epam.jwd.controller.command.brigade;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.BrigadeDTO;
import com.epam.jwd.service.dto.BrigadeUserDTO;
import com.epam.jwd.service.impl.BrigadeService;
import com.epam.jwd.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ShowBrigadeWithUsersPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowBrigadeWithUsersPageCommand.class);
    private static final String BRIGADE_WITH_USERS_DTO_ATTRIBUTE_NAME = "brigadeUserDTO";
    private static final String BRIGADE_ID_ATTRIBUTE_NAME = "brigade_id";
    private static final Command INSTANCE = new ShowBrigadeWithUsersPageCommand();
    private static final String BRIGADES_USERS_JSP = "/WEB-INF/jsp/brigade_with_user.jsp";
    private static final ResponseContext SHOW_BRIGADES_USERS_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return BRIGADES_USERS_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };


    private ShowBrigadeWithUsersPageCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        BrigadeService brigadeService = new BrigadeService();
        UserService userService = new UserService();
        try {
            long id = Long.parseLong(requestContext.getParamFromJSP(BRIGADE_ID_ATTRIBUTE_NAME));
            BrigadeDTO brigadeDTO = brigadeService.findById(id);
            BrigadeUserDTO brigadeUserDTO = brigadeService.getBrigadeWithUsers(brigadeDTO);
            requestContext.addAttributeToJSP(BRIGADE_WITH_USERS_DTO_ATTRIBUTE_NAME, brigadeUserDTO);
            requestContext.addAttributeToJSP("brigadeDTO", brigadeDTO);
            requestContext.addAttributeToJSP("userDTOList", userService.findFreeWorkers(brigadeDTO.getBrigadeId()));
        } catch (DAOException | NumberFormatException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE_NAME, e.getMessage());
        }
        return SHOW_BRIGADES_USERS_PAGE_CONTEXT;
    }
}
