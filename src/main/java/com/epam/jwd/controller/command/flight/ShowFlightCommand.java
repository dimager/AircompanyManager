package com.epam.jwd.controller.command.flight;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.BrigadeDTO;
import com.epam.jwd.service.dto.FlightDTO;
import com.epam.jwd.service.impl.BrigadeService;
import com.epam.jwd.service.impl.FlightService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ShowFlightCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowFlightCommand.class);
    public static final String FLIGHT_JSP = "/WEB-INF/jsp/flights.jsp";
    private static final Command INSTANCE = new ShowFlightCommand();
    private static final Object ERROR_CODE = 134;

    private static final ResponseContext SHOW_FLIGHTS_COMMAND_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return FLIGHT_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    ShowFlightCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        BrigadeService brigadeService = new BrigadeService();
        FlightService flightService = new FlightService();
        try {
            List<BrigadeDTO>  brigadeDTOList = brigadeService.findAllBrigade();
            List<FlightDTO> flightDTOList = flightService.findAllFlights();
            for (FlightDTO flightDTO : flightDTOList) {
                if (!flightDTO.getAircraftDTO().getInOperation()){
                        requestContext.addAttributeToJSP(Attributes.COMMAND_ONEERROR_ATTRIBUTE,  ERROR_CODE);
                    }
            }
            requestContext.addAttributeToJSP(Attributes.FLIGHT_DTO_LIST_ATTRIBUTE, flightDTOList);
            requestContext.addAttributeToJSP(Attributes.BRIGADE_DTO_LIST_ATTRIBUTE, brigadeDTOList);

        } catch (DAOException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
        }

        return SHOW_FLIGHTS_COMMAND_CONTEXT;
    }


}
