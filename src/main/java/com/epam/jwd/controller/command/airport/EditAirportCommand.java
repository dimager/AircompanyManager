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

public class EditAirportCommand implements Command {
    private static final Logger logger = LogManager.getLogger(EditAirportCommand.class);
    private static final Command INSTANCE = new EditAirportCommand();
    private static final String EDIT_AIRPORT_JSP = "/controller?command=SHOW_AIRPORT_PAGE";
    private static final int RESULT_MESSAGE_CODE = 118;
    private static final int PARSING_ERROR_CODE = 247;

    private static final ResponseContext EDIT_AIRPORT_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return EDIT_AIRPORT_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private EditAirportCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        logger.debug("execute method");
        AirportService airportService = new AirportService();
        AirportDTO airportDTO = new AirportDTO();
        airportDTO.setName(requestContext.getParamFromJSP(Attributes.AIRPORTS_DTO_NAME_ATTRIBUTE));
        airportDTO.setCountry(requestContext.getParamFromJSP(Attributes.AIRPORTS_DTO_COUNTRY_ATTRIBUTE));
        airportDTO.setCity(requestContext.getParamFromJSP(Attributes.AIRPORTS_DTO_CITY_ATTRIBUTE));
        airportDTO.setIATACode(requestContext.getParamFromJSP(Attributes.AIRPORTS_DTO_IATACODE_ATTRIBUTE));
        try {
            airportDTO.setId(Integer.parseInt(requestContext.getParamFromJSP(Attributes.AIRPORTS_DTO_ID_ATTRIBUTE)));
            airportService.updateAirport(airportDTO);
            requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE,  RESULT_MESSAGE_CODE);
        } catch (DAOException | ValidatorException e ) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
        } catch (NumberFormatException e ) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, PARSING_ERROR_CODE);
        }
        return EDIT_AIRPORT_CONTEXT;
    }

}
