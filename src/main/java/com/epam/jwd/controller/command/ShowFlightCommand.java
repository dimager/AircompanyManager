package com.epam.jwd.controller.command;

import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.entity.Flight;
import com.epam.jwd.service.Service;
import com.epam.jwd.service.impl.FlightService;
import com.epam.jwd.controller.context.RequestContext;

import java.util.List;

public class ShowFlightCommand implements Command {
    private static final Command INSTANCE = new ShowFlightCommand();
    public static final String FLIGHT_JSP = "WEB-INF/flights.jsp";
    private final Service<Flight> service = new FlightService();
    public static Command getInstance(){
        return INSTANCE;
    }
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

    private ShowFlightCommand() {
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        List<Flight> flights = service.findAll();
        return SHOW_FLIGHTS_COMMAND_CONTEXT;
    }


}
