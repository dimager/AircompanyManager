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
            return false;
        }
    };

    private AddAircraftCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        logger.debug("execute method");
        AircraftService aircraftService = new AircraftService();
        AircraftDTO aircraftDTO = new AircraftDTO();
        String producer = requestContext.getParamFromJSP(Attributes.AIRCRAFT_PRODUCER_ATTRIBUTE);
        String model = requestContext.getParamFromJSP(Attributes.AIRCRAFT_MODEL_ATTRIBUTE);
        String registrationCode = requestContext.getParamFromJSP(Attributes.AIRCRAFT_MODEL_ATTRIBUTE);
        aircraftDTO.setProducer(producer);
        aircraftDTO.setModel(model);
        aircraftDTO.setRegistrationCode(registrationCode);
        aircraftDTO.setInOperation(true);
        try {
            aircraftService.saveAircraft(aircraftDTO);
            requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE, RESULT_MESSAGE_CODE);
        } catch (DAOException | ValidatorException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
        }

        return ADD_AIRCRAFT_PAGE_CONTEXT;
    }
}
