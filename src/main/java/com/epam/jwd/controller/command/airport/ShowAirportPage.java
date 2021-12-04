package com.epam.jwd.controller.command.airport;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.AircraftDTO;
import com.epam.jwd.service.dto.AirportDTO;
import com.epam.jwd.service.exception.ValidatorException;
import com.epam.jwd.service.impl.AircraftService;
import com.epam.jwd.service.impl.AirportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ShowAirportPage implements Command {
    private static final Logger logger = LogManager.getLogger(ShowAirportPage.class);
    private static final Command INSTANCE = new ShowAirportPage();
    private static final String AIRPORT_JSP = "/WEB-INF/jsp/airports.jsp";

    private static final ResponseContext CHANGE_PASSWORD_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return AIRPORT_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private ShowAirportPage() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        AirportService airportService = new AirportService();

        try {
            List<AirportDTO>  airportDTOList = airportService.findAllAirports();
            requestContext.addAttributeToJSP(Attributes.AIRPORTS_DTO_LIST_ATTRIBUTE_NAME, airportDTOList);

        } catch (DAOException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE_NAME, e.getMessage());
        }
        return CHANGE_PASSWORD_PAGE_CONTEXT;
    }
}
