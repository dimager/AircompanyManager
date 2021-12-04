package com.epam.jwd.controller.command;

public interface Attributes {
    String COMMAND_RESULT_ATTRIBUTE_NAME = "commandResult";
    String EXCEPTION_ATTRIBUTE_NAME = "exception";

    String AIRCRAFT_PRODUCER_ATTRIBUTE_NAME = "aircraftProducer";
    String AIRCRAFT_MODEL_ATTRIBUTE_NAME = "aircraftModel";
    String REG_CODE_ATTRIBUTE_NAME = "regCode";
    String AIRCRAFT_DTO_ATTRIBUTE_NAME = "aircraftDTO";
    String DELETE_AIRCRAFT_ID_ATTRIBUTE_NAME = "delete_aircraft_id";
    String AIRCRAFT_ID_ATTRIBUTE_NAME = "aircraftId";
    String EDIT_AIRCRAFT_ATTRIBUTE_NAME = "edit_aircraft_id";
    String EDIT_PAGE_BOOLEAN_ATTRIBUTE_NAME = "editpage";
    String AIRCRAFT_DTO_LIST_ATTRIBUTE_NAME = "aircraftDTOList";
    String CALLSIGN_INPUT_FIELD = "callsignInput";
    String SELECTED_DATE_TIME = "selectedDateTime";
    String SELECTED_BRIGADE_FIELD_NAME = "selectedBrigade";
    String SELECTED_AIRCRAFT_FIELD_NAME = "selectedAircraft";
    String SELECTED_DEPARTURE_AIRPORT_FIELD_NAME = "selectedDepartureAirport";
    String SELECTED_DESTINATION_AIRPORT_FIELD_NAME = "selectedDestinationAirport";
    String EDIT_FLIGHT_ID_ATTRIBUTE_NAME = "edit_flight_id";
    String DELETE_FLIGHT_ID_ATTRIBUTE_NAME = "delete_flight_id";
    String NEW_BRIGADE_ID_ATTRIBUTE_NAME = "new_brigade";
    String FLIGHT_DTO_LIST_ATTRIBUTE_NAME = "flightDTOList";
    String BRIGADE_DTO_LIST_ATTRIBUTE_NAME = "brigadeDTOList";

    String JSP_USERNAME_INPUT_FILED_NAME = "username";
    String JSP_FIRSTNAME_INPUT_FILED_NAME = "firstname";
    String JSP_LASTNAME_INPUT_FILED_NAME = "lastname";
    String JSP_EMAIL_INPUT_FILED_NAME = "email";
    String JSP_PASSWORD_INPUT_FILED_NAME = "password";
    String JSP_PASSWORD_REPEAT_INPUT_FILED_NAME = "password-repeat";

    String SESSION_LOGIN_STATE_ATTRIBUTE_NAME = "loginState";
    String SESSION_USER_ATTRIBUTE = "loggedinUser";
    String SESSION_LOCALE_ATTRIBUTE_NAME = "sessionLocale";
    String BRIGADENAME_ATTRIBUTE_NAME = "brigadename";
    String AIRPORTS_DTO_LIST_ATTRIBUTE_NAME = "airportDTOList";
    String AIRPORTS_DTO_ATTRIBUTE_NAME = "airportDTO";
    String AIRPORTS_DTO_NAME_ATTRIBUTE_NAME = "airportName";
    String AIRPORTS_DTO_COUNTRY_ATTRIBUTE_NAME = "airportCountry";
    String AIRPORTS_DTO_CITY_ATTRIBUTE_NAME = "airportCity";
    String AIRPORTS_DTO_IATACODE_ATTRIBUTE_NAME = "airportIATAcode";
    String AIRPORTS_DTO_ID_ATTRIBUTE_NAME = "edit_airport_id";
    String DELETE_AIRPORT_ID_ATTRIBUTE_NAME = "delete_airport_id";
    String COMMAND_ERRORS_ATTRIBUTE_NAME = "errors";
    String USER_BRIGADES_ATTRIBUTE_NAME = "userBrigades";
}
