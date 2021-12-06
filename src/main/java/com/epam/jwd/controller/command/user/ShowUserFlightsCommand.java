package com.epam.jwd.controller.command.user;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.FlightDTO;
import com.epam.jwd.service.dto.UserDTO;
import com.epam.jwd.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ShowUserFlightsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowUserFlightsCommand.class);
    private static final Command INSTANCE = new ShowUserFlightsCommand();
    private static final String USER_FLIGHTS_JSP = "/WEB-INF/jsp/userflights.jsp";
    private static final ResponseContext SHOW_USER_FLIGHTS_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return USER_FLIGHTS_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private ShowUserFlightsCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        logger.debug("execute method");
        UserDTO userDTO;
        UserService userService = new UserService();
        try {
            String userId = requestContext.getParamFromJSP(Attributes.SHOW_USER_FLIGHTS_ID_ATTRIBUTE);
            if (Objects.nonNull(userId)) {
                userDTO = userService.findById(Long.parseLong(userId));
            } else {
                userDTO = (UserDTO) requestContext.getHttpSession().getAttribute(Attributes.SESSION_USER_ATTRIBUTE);
            }

            if (Objects.nonNull(userDTO)) {
                List<FlightDTO> flightDTOList = userService.findUserFlights(userDTO.getUserId()).stream()
                        .sorted(Comparator.comparing(FlightDTO::getDepartureDateTime))
                        .collect(Collectors.toList());
                requestContext.addAttributeToJSP(Attributes.FLIGHT_DTO_LIST_ATTRIBUTE, flightDTOList);
            }

            requestContext.addAttributeToJSP(Attributes.USER_DTO_ATTRIBUTE, userDTO);
        } catch (DAOException | NumberFormatException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
        }

        return SHOW_USER_FLIGHTS_PAGE_CONTEXT;
    }
}
