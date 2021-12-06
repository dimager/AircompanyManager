package com.epam.jwd.controller.command.flight;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.AircraftDTO;
import com.epam.jwd.service.dto.AirportDTO;
import com.epam.jwd.service.dto.BrigadeDTO;
import com.epam.jwd.service.dto.FlightDTO;
import com.epam.jwd.service.impl.AircraftService;
import com.epam.jwd.service.impl.AirportService;
import com.epam.jwd.service.impl.BrigadeService;
import com.epam.jwd.service.impl.FlightService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

public class ShowAddEditFlightCommand implements Command {
    public static final String FLIGHT_JSP = "/WEB-INF/jsp/add_edit_flight.jsp";
    private static final Logger logger = LogManager.getLogger(ShowAddEditFlightCommand.class);
    private static final Command INSTANCE = new ShowAddEditFlightCommand();
    private static final String DATE_TIME_PATTERN = "MM/dd/yyyy h:mm a";
    private static final ResponseContext SHOW_ADD_EDIT_FLIGHT_COMMAND_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return FLIGHT_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    ShowAddEditFlightCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        logger.debug("execute method");
        AirportService airportService = new AirportService();
        BrigadeService brigadeService = new BrigadeService();
        AircraftService aircraftService = new AircraftService();
        FlightService flightService = new FlightService();
        try {
            List<AirportDTO> airportDTOList = airportService.findAllAirports();
            List<BrigadeDTO> brigadeDTOList = brigadeService.findAllBrigade();
            List<AircraftDTO> aircraftDTOList = aircraftService.findAllAircrafts();
            requestContext.addAttributeToJSP(Attributes.AIRCRAFT_DTO_LIST_ATTRIBUTE, aircraftDTOList);
            requestContext.addAttributeToJSP(Attributes.BRIGADE_DTO_LIST_ATTRIBUTE, brigadeDTOList);
            requestContext.addAttributeToJSP(Attributes.AIRPORTS_DTO_LIST_ATTRIBUTE, airportDTOList);
            if (Objects.nonNull(requestContext.getParamFromJSP(Attributes.EDIT_FLIGHT_ID_ATTRIBUTE))) {
                requestContext.addAttributeToJSP(Attributes.EDIT_PAGE_BOOLEAN_ATTRIBUTE, true);
                long id = Long.parseLong(requestContext.getParamFromJSP(Attributes.EDIT_FLIGHT_ID_ATTRIBUTE));
                FlightDTO flightDTO = flightService.findFlightById(id);
                requestContext.addAttributeToJSP(Attributes.FLIGHT_DTO_ATTRIBUTE, flightDTO);
                SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_PATTERN);
                requestContext.addAttributeToJSP(Attributes.FLIGHTSTIME_ATTRIBUTE,format.format(flightDTO.getDepartureDateTime()));
            }
        } catch (DAOException | NumberFormatException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
        }

        return SHOW_ADD_EDIT_FLIGHT_COMMAND_CONTEXT;

    }
}