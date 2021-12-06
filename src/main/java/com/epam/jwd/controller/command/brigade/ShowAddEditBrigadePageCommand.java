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

import java.util.Objects;

public class ShowAddEditBrigadePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowAddEditBrigadePageCommand.class);
    private static final Command INSTANCE = new ShowAddEditBrigadePageCommand();
    private static final String ADD_EDIT_JSP = "/WEB-INF/jsp/add_edit_brigade.jsp";
    private static final ResponseContext SHOW_ADD_EDIT_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return ADD_EDIT_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private ShowAddEditBrigadePageCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        logger.debug("execute method");
        if (Objects.nonNull(requestContext.getParamFromJSP(Attributes.EDIT_BRIGADE_ID_ATTRIBUTE))) {
            requestContext.addAttributeToJSP(Attributes.EDIT_PAGE_BOOLEAN_ATTRIBUTE,true);
            BrigadeService brigadeService = new BrigadeService();
            BrigadeDTO brigadeDTO;
            try {
                long id = Long.parseLong(requestContext.getParamFromJSP(Attributes.EDIT_BRIGADE_ID_ATTRIBUTE));
                brigadeDTO = brigadeService.findById(id);
                requestContext.addAttributeToJSP(Attributes.BRIGADE_DTO_ATTRIBUTE, brigadeDTO);

            } catch (DAOException |  NumberFormatException e) {
                logger.error(e);
                requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
            }
        }

        return SHOW_ADD_EDIT_PAGE_CONTEXT;
    }
}
