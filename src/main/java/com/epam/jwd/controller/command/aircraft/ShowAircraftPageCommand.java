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

import java.util.List;

public class ShowAircraftPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowAircraftPageCommand.class);
    private static final Command INSTANCE = new ShowAircraftPageCommand();
    private static final String AIRCRAFT_PAGE_JSP = "/WEB-INF/jsp/aircrafts.jsp";
    private static final ResponseContext SHOW_AIRCRAFT_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return AIRCRAFT_PAGE_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private ShowAircraftPageCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        AircraftService aircraftService = new AircraftService();
        try {
            List<AircraftDTO> aircraftDTOList = aircraftService.findAllAircrafts();
            requestContext.addAttributeToJSP(Attributes.AIRCRAFT_DTO_LIST_ATTRIBUTE_NAME, aircraftDTOList);
        } catch (DAOException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE_NAME, e.getMessage());
        }
        return SHOW_AIRCRAFT_PAGE_CONTEXT;
    }


}
