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
    private static final String ADD_BRIGADE_JSP = "/controller?command=SHOW_BRIGADE_PAGE";
    private static final int RESULT_MESSAGE_CODE = 106;

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
        logger.debug("execute method");
        BrigadeDTO brigadeDTO = new BrigadeDTO();
        BrigadeService brigadeService = new BrigadeService();
        brigadeDTO.setBrigadeName(requestContext.getParamFromJSP(Attributes.BRIGADENAME_ATTRIBUTE));
        try {
            brigadeService.saveBrigade(brigadeDTO);
            requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE,  RESULT_MESSAGE_CODE);
        } catch (DAOException | ValidatorException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
        }
        return ADD_BRIGADES_PAGE_CONTEXT;
    }
}
