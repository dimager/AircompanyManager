package repository.entity;

import java.time.LocalDateTime;

public class Flight implements Entity {
    private long id;
    private Aircraft flightAircraft;
    private Brigade flightBrigade;
    private String flightCallsign;
    private String departureAirport;
    private String destinationAirport;
    private LocalDateTime departureDateTime;

}
