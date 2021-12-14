package com.epam.jwd.controller.command.brigade;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.entity.Role;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.BrigadeDTO;
import com.epam.jwd.service.dto.BrigadeUserDTO;
import com.epam.jwd.service.dto.UserDTO;
import com.epam.jwd.service.impl.BrigadeService;
import com.epam.jwd.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;


public class ShowBrigadeWithUsersArchivePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowBrigadeWithUsersArchivePageCommand.class);
    private static final Command INSTANCE = new ShowBrigadeWithUsersArchivePageCommand();
    private static final int ERROR_CODE = 136;
    private static final int ERROR_CODE_USER_PERMISSION_DENY = 137;
    private static final int PARSING_ERROR_CODE = 247;
    private static final String BRIGADES_USERS_JSP = "/WEB-INF/jsp/archive_brigade_with_user.jsp";
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


    private ShowBrigadeWithUsersArchivePageCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        logger.debug("execute method");
        BrigadeService brigadeService = new BrigadeService();
        UserService userService = new UserService();
        UserDTO sessionUserDTO = (UserDTO) requestContext.getHttpSession(false).getAttribute(Attributes.SESSION_USER_ATTRIBUTE);
        try {
            String brigadeIdValue = requestContext.getParamFromJSP(Attributes.BRIGADE_ID_ATTRIBUTE);
            if (Objects.nonNull(brigadeIdValue)) {
                long brigadeId = Long.parseLong(brigadeIdValue);
                BrigadeDTO brigadeDTO = brigadeService.findById(brigadeId);
                List<BrigadeDTO> userBrigades = userService.findArchivedUserBrigades(sessionUserDTO.getUserId());
                if (sessionUserDTO.getRole().equals(Role.MANAGER) || userBrigades.contains(brigadeDTO)){
                    BrigadeUserDTO brigadeUserDTO = brigadeService.getBrigadeWithUsers(brigadeDTO);
                    requestContext.addAttributeToJSP(Attributes.BRIGADE_WITH_USERS_DTO_ATTRIBUTE, brigadeUserDTO);
                }
                else{
                    requestContext.addAttributeToJSP(Attributes.COMMAND_ONEERROR_ATTRIBUTE, ERROR_CODE_USER_PERMISSION_DENY);
                }
            } else {
                requestContext.addAttributeToJSP(Attributes.COMMAND_ONEERROR_ATTRIBUTE, ERROR_CODE);
            }
        } catch (DAOException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
        }  catch (NumberFormatException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, PARSING_ERROR_CODE);
        }
        return SHOW_BRIGADES_USERS_PAGE_CONTEXT;
    }
}
