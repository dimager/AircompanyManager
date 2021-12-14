package com.epam.jwd.controller.command.aircraft;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.AircraftDTO;
import com.epam.jwd.service.impl.AircraftService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class ShowAddEditAircraftPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowAddEditAircraftPageCommand.class);
    private static final Command INSTANCE = new ShowAddEditAircraftPageCommand();
    private static final String ADD_EDIT_AIRCRAFT_JSP = "/WEB-INF/jsp/add_edit_aircraft.jsp";
    private static final int PARSING_ERROR_CODE = 247;

    private static final ResponseContext SHOW_ADD_EDIT_AIRCRAFT_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return ADD_EDIT_AIRCRAFT_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private ShowAddEditAircraftPageCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        logger.debug("execute method");
        if (Objects.nonNull(requestContext.getParamFromJSP(Attributes.EDIT_AIRCRAFT_ATTRIBUTE))) {
            requestContext.addAttributeToJSP(Attributes.EDIT_PAGE_BOOLEAN_ATTRIBUTE,true);
            AircraftService aircraftService = new AircraftService();
            AircraftDTO editAircraftDTO;
            try {
                int id = Integer.parseInt(requestContext.getParamFromJSP(Attributes.EDIT_AIRCRAFT_ATTRIBUTE));
                editAircraftDTO = aircraftService.findAircraftById(id);
                requestContext.addAttributeToJSP(Attributes.AIRCRAFT_DTO_ATTRIBUTE, editAircraftDTO);
            } catch (DAOException e) {
                logger.error(e);
                requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
            } catch (NumberFormatException e) {
                logger.error(e);
                requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, PARSING_ERROR_CODE);
            }

        }
        return SHOW_ADD_EDIT_AIRCRAFT_PAGE_CONTEXT;
    }

}
