package com.epam.jwd.controller.command.aircraft;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.AircraftDTO;
import com.epam.jwd.service.exception.ValidatorException;
import com.epam.jwd.service.impl.AircraftService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddAircraftCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddAircraftCommand.class);
    private static final Command INSTANCE = new AddAircraftCommand();
    private static final String ADD_AIRCRAFT_JSP = "/controller?command=SHOW_AIRCRAFT_PAGE";

    private static final int RESULT_MESSAGE_CODE = 101;

    private static final ResponseContext ADD_AIRCRAFT_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return ADD_AIRCRAFT_JSP;
        }

        @Override
        public boolean isRedirect() {
            return true;
        }
    };

    private AddAircraftCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        AircraftService aircraftService = new AircraftService();
        AircraftDTO aircraftDTO = new AircraftDTO();
        aircraftDTO.setProducer(requestContext.getParamFromJSP(Attributes.AIRCRAFT_PRODUCER_ATTRIBUTE_NAME).trim());
        aircraftDTO.setModel(requestContext.getParamFromJSP(Attributes.AIRCRAFT_MODEL_ATTRIBUTE_NAME).trim());
        aircraftDTO.setRegistrationCode(requestContext.getParamFromJSP(Attributes.REG_CODE_ATTRIBUTE_NAME).trim());

        try {
            aircraftDTO = aircraftService.saveAircraft(aircraftDTO);
            requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE_NAME,  RESULT_MESSAGE_CODE);
        } catch (DAOException | ValidatorException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE_NAME, e.getMessage());
            requestContext.addAttributeToJSP(Attributes.AIRCRAFT_DTO_ATTRIBUTE_NAME, aircraftDTO);
        }
        return ADD_AIRCRAFT_PAGE_CONTEXT;
    }
}
