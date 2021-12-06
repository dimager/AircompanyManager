package com.epam.jwd.controller.command.brigade;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.BrigadeUserDTO;
import com.epam.jwd.service.impl.BrigadeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ShowBrigadePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowAddEditBrigadePageCommand.class);
    private static final Command INSTANCE = new ShowBrigadePageCommand();
    private static final String BRIGADE_JSP = "/WEB-INF/jsp/brigades.jsp";
    private static final ResponseContext SHOW_BRIGADES_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return BRIGADE_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private ShowBrigadePageCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        logger.debug("execute method");
        BrigadeService brigadeService = new BrigadeService();
        try {
            List<BrigadeUserDTO> brigadeUserDTOList = brigadeService.findAllBrigadeWithUsers();
            requestContext.addAttributeToJSP(Attributes.BRIGADE_USER_DTO_LIST, brigadeUserDTOList);
        } catch (DAOException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
        }
        return SHOW_BRIGADES_PAGE_CONTEXT;
    }

}
