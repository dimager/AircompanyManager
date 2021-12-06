package com.epam.jwd.controller.command;

public interface Attributes {
    String COMMAND_RESULT_ATTRIBUTE = "commandResult";
    String EXCEPTION_ATTRIBUTE = "exception";

    String AIRCRAFT_PRODUCER_ATTRIBUTE = "aircraftProducer";
    String AIRCRAFT_MODEL_ATTRIBUTE = "aircraftModel";
    String REG_CODE_ATTRIBUTE = "regCode";
    String AIRCRAFT_DTO_ATTRIBUTE = "aircraftDTO";
    String DELETE_AIRCRAFT_ID_ATTRIBUTE = "delete_aircraft_id";
    String AIRCRAFT_ID_ATTRIBUTE = "aircraftId";
    String EDIT_AIRCRAFT_ATTRIBUTE = "edit_aircraft_id";
    String EDIT_PAGE_BOOLEAN_ATTRIBUTE = "editpage";
    String AIRCRAFT_DTO_LIST_ATTRIBUTE = "aircraftDTOList";
    String CALLSIGN_INPUT_FIELD = "callsignInput";
    String SELECTED_DATE_TIME = "selectedDateTime";
    String SELECTED_BRIGADE_FIELD_NAME = "selectedBrigade";
    String SELECTED_AIRCRAFT_FIELD_NAME = "selectedAircraft";
    String SELECTED_DEPARTURE_AIRPORT_FIELD_NAME = "selectedDepartureAirport";
    String SELECTED_DESTINATION_AIRPORT_FIELD_NAME = "selectedDestinationAirport";
    String EDIT_FLIGHT_ID_ATTRIBUTE = "edit_flight_id";
    String DELETE_FLIGHT_ID_ATTRIBUTE = "delete_flight_id";
    String NEW_BRIGADE_ID_ATTRIBUTE = "new_brigade";
    String FLIGHT_DTO_LIST_ATTRIBUTE = "flightDTOList";
    String FLIGHT_DTO_ATTRIBUTE = "flightDTO";
    String BRIGADE_DTO_LIST_ATTRIBUTE = "brigadeDTOList";

    String JSP_USERNAME_INPUT_FILED_NAME = "username";
    String JSP_FIRSTNAME_INPUT_FILED_NAME = "firstname";
    String JSP_LASTNAME_INPUT_FILED_NAME = "lastname";
    String JSP_EMAIL_INPUT_FILED_NAME = "email";
    String JSP_PASSWORD_INPUT_FILED_NAME = "password";
    String JSP_PASSWORD_REPEAT_INPUT_FILED_NAME = "password-repeat";

    String SESSION_LOGIN_STATE_ATTRIBUTE = "loginState";
    String SESSION_USER_ATTRIBUTE = "loggedinUser";
    String SESSION_LOCALE_ATTRIBUTE = "sessionLocale";
    String BRIGADENAME_ATTRIBUTE = "brigadename";
    String AIRPORTS_DTO_LIST_ATTRIBUTE = "airportDTOList";
    String AIRPORTS_DTO_NAME_ATTRIBUTE = "airportName";
    String AIRPORTS_DTO_COUNTRY_ATTRIBUTE = "airportCountry";
    String AIRPORTS_DTO_CITY_ATTRIBUTE = "airportCity";
    String AIRPORTS_DTO_IATACODE_ATTRIBUTE = "airportIATAcode";
    String AIRPORTS_DTO_ID_ATTRIBUTE = "edit_airport_id";
    String DELETE_AIRPORT_ID_ATTRIBUTE = "delete_airport_id";
    String COMMAND_ERRORS_ATTRIBUTE = "errors";
    String USER_BRIGADES_ATTRIBUTE = "userBrigades";
    String RETURN_PAGE_ATTRIBUTE = "returnPage";
    String LOCALE_ATTRIBUTE = "lang";
    String SHOW_USER_FLIGHTS_ID_ATTRIBUTE = "show_user_id";
    String USER_DTO_ATTRIBUTE = "userDTO";
    String USER_ID_ATTRIBUTE = "user_id";
    String BRIGADE_ID_ATTRIBUTE = "brigade_id";
    String EDIT_BRIGADE_ID_ATTRIBUTE = "edit_brigade_id";
    String BRIGADE_DTO_ATTRIBUTE = "brigadeDTO";
    String USER_DTO_LIST_ATTRIBUTE = "userDTOList";
    String BRIGADE_USER_DTO_LIST = "brigadeUserDTOList";
    String FLIGHTSTIME_ATTRIBUTE = "flightTime";
    String OLD_PASSWORD_ATTRIBUTE = "oldPassword";
    String NEW_PASSWORD_ATTRIBUTE = "newPassword";
    String NEW_PASSWORD_REPEAT_ATTRIBUTE = "newPasswordRepeat";
    String NEW_ROLE_ATTRIBUTE = "new_role";
    String ROLES_ATTRIBUTE = "roles";
}
