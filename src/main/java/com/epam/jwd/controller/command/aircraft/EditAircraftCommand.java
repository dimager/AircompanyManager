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

public class EditAircraftCommand implements Command {
    private static final Logger logger = LogManager.getLogger(EditAircraftCommand.class);
    private static final Command INSTANCE = new EditAircraftCommand();
    private static final String EDIT_AIRCRAFT_JSP = "/controller?command=SHOW_AIRCRAFT_PAGE";
    private static final int RESULT_MESSAGE_CODE = 122;
    private static final ResponseContext EDIT_AIRCRAFT_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return EDIT_AIRCRAFT_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private EditAircraftCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        logger.debug("execute method");
        AircraftService aircraftService = new AircraftService();
        AircraftDTO aircraftDTO = new AircraftDTO();
        aircraftDTO.setProducer(requestContext.getParamFromJSP(Attributes.AIRCRAFT_PRODUCER_ATTRIBUTE).trim());
        aircraftDTO.setModel(requestContext.getParamFromJSP(Attributes.AIRCRAFT_MODEL_ATTRIBUTE).trim());
        aircraftDTO.setRegistrationCode(requestContext.getParamFromJSP(Attributes.REG_CODE_ATTRIBUTE).trim());
        aircraftDTO.setAircraftId(Integer.parseInt(requestContext.getParamFromJSP(Attributes.AIRCRAFT_ID_ATTRIBUTE)));
        try {
            aircraftService.updateAircraft(aircraftDTO);
            requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE, RESULT_MESSAGE_CODE);
        } catch (DAOException | ValidatorException |  NumberFormatException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.AIRCRAFT_DTO_ATTRIBUTE, aircraftDTO);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
        }
        return EDIT_AIRCRAFT_CONTEXT;
    }

}
