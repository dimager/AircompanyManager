package com.epam.jwd.controller.command.brigade;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.BrigadeDTO;
import com.epam.jwd.service.exception.ValidatorException;
import com.epam.jwd.service.impl.BrigadeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddBrigadeCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddBrigadeCommand.class);
    private static final Command INSTANCE = new AddBrigadeCommand();
    private static final String ADD_BRIGADE_JSP = "/WEB-INF/jsp/add_edit_brigade.jsp";
    private static final String RESULT_MESSAGE = "Brigade is added";

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

    private AddBrigadeCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        BrigadeDTO brigadeDTO = new BrigadeDTO();
        BrigadeService brigadeService = new BrigadeService();
        brigadeDTO.setBrigadeName(requestContext.getParamFromJSP("brigadename"));
        try {
            brigadeService.saveBrigade(brigadeDTO);
            requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE_NAME,  RESULT_MESSAGE);
        } catch (DAOException | ValidatorException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE_NAME, e.getMessage());
        }
        return ADD_BRIGADES_PAGE_CONTEXT;
    }
}
