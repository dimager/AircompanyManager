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

public class EditBrigadeCommand implements Command {
    private static final Logger logger = LogManager.getLogger(EditBrigadeCommand.class);
    private static final Command INSTANCE = new EditBrigadeCommand();
    private static final String EDIT_BRIGADE_JSP = "/controller?command=SHOW_BRIGADE_PAGE";
    private static final int RESULT_MESSAGE_CODE = 110;

    private static final ResponseContext EDIT_BRIGADES_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return EDIT_BRIGADE_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private EditBrigadeCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        BrigadeDTO brigadeDTO = new BrigadeDTO();
        BrigadeService brigadeService = new BrigadeService();
        brigadeDTO.setBrigadeName(requestContext.getParamFromJSP("brigadename"));
        brigadeDTO.setBrigadeId(Long.parseLong(requestContext.getParamFromJSP("brigadeId")));
        try {
            brigadeService.updateBrigade(brigadeDTO);
            requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE_NAME,  RESULT_MESSAGE_CODE);
        } catch (DAOException | ValidatorException |  NumberFormatException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE_NAME, e.getMessage());
        }
        return EDIT_BRIGADES_PAGE_CONTEXT;
    }
}
