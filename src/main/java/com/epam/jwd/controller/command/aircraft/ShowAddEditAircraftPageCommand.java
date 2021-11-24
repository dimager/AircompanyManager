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
        if (Objects.nonNull(requestContext.getParamFromJSP(Attributes.EDIT_AIRCRAFT_ATTRIBUTE_NAME))) {
            requestContext.addAttributeToJSP(Attributes.EDIT_PAGE_BOOLEAN_ATTRIBUTE_NAME,true);
            AircraftService aircraftService = new AircraftService();
            AircraftDTO editAircraftDTO;
            try {
                int id = Integer.parseInt(requestContext.getParamFromJSP(Attributes.EDIT_AIRCRAFT_ATTRIBUTE_NAME));
                editAircraftDTO = aircraftService.findAircraftById(id);
                requestContext.addAttributeToJSP(Attributes.AIRCRAFT_DTO_ATTRIBUTE_NAME, editAircraftDTO);
            } catch (DAOException | NumberFormatException e) {
                logger.error(e);
                requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE_NAME, e.getMessage());
            }

        }
        return SHOW_ADD_EDIT_AIRCRAFT_PAGE_CONTEXT;
    }

}
