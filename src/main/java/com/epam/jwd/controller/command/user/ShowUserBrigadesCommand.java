package com.epam.jwd.controller.command.user;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.BrigadeDTO;
import com.epam.jwd.service.dto.BrigadeUserDTO;
import com.epam.jwd.service.dto.UserDTO;
import com.epam.jwd.service.impl.BrigadeService;
import com.epam.jwd.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShowUserBrigadesCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowUserBrigadesCommand.class);
    private static final Command INSTANCE = new ShowUserBrigadesCommand();
    private static final String USER_BRIGADES_JSP = "/WEB-INF/jsp/userbrigades.jsp";
    private static final ResponseContext SHOW_USER_FLIGHTS_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return USER_BRIGADES_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private ShowUserBrigadesCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        logger.debug("execute method");
        UserDTO userDTO;
        UserService userService = new UserService();
        BrigadeService brigadeService = new BrigadeService();
        List<BrigadeUserDTO> brigadeUserDTOList = new ArrayList<>();
        try {

            String userId = requestContext.getParamFromJSP(Attributes.SHOW_USER_FLIGHTS_ID_ATTRIBUTE);
            if (Objects.nonNull(userId)) {
                userDTO = userService.findById(Long.parseLong(userId));
            } else {
                userDTO = (UserDTO) requestContext.getHttpSession(false).getAttribute(Attributes.SESSION_USER_ATTRIBUTE);
            }
            List<BrigadeDTO> brigadeDTOList = userService.findUserBrigades(userDTO.getUserId());
            for (BrigadeDTO brigadeDTO : brigadeDTOList) {
                brigadeUserDTOList.add(brigadeService.getBrigadeWithUsers(brigadeDTO));
            }
            requestContext.addAttributeToJSP(Attributes.USER_BRIGADES_ATTRIBUTE, brigadeUserDTOList);
            requestContext.addAttributeToJSP(Attributes.USER_DTO_ATTRIBUTE, userDTO);
        } catch (DAOException | NumberFormatException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
        }
        return SHOW_USER_FLIGHTS_PAGE_CONTEXT;
    }
}
