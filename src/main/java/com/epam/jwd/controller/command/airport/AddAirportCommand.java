package com.epam.jwd.controller.command.airport;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.AirportDTO;
import com.epam.jwd.service.exception.ValidatorException;
import com.epam.jwd.service.impl.AirportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddAirportCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddAirportCommand.class);
    private static final Command INSTANCE = new AddAirportCommand();
    private static final String ADD_AIRPORT_JSP = "/controller?command=SHOW_AIRPORT_PAGE";
    private static final int RESULT_MESSAGE_CODE = 117;

    private static final ResponseContext ADD_AIRPORT_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return ADD_AIRPORT_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private AddAirportCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        logger.debug("execute method");
        AirportService airportService = new AirportService();
        AirportDTO airportDTO = new AirportDTO();
        airportDTO.setName(requestContext.getParamFromJSP(Attributes.AIRPORTS_DTO_NAME_ATTRIBUTE).trim());
        airportDTO.setCountry(requestContext.getParamFromJSP(Attributes.AIRPORTS_DTO_COUNTRY_ATTRIBUTE).trim());
        airportDTO.setCity(requestContext.getParamFromJSP(Attributes.AIRPORTS_DTO_CITY_ATTRIBUTE).trim());
        airportDTO.setIATACode(requestContext.getParamFromJSP(Attributes.AIRPORTS_DTO_IATACODE_ATTRIBUTE).trim());
        try {
            airportService.saveAirport(airportDTO);
            requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE,  RESULT_MESSAGE_CODE);
        } catch (DAOException | ValidatorException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
        }
        return ADD_AIRPORT_PAGE_CONTEXT;
    }
}
