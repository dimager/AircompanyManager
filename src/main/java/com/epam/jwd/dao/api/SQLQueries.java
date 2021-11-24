package com.epam.jwd.dao.api;

public interface SQLQueries {
    //Aircrafts sql
    String SQL_AIRCRAFTS_SELECT_ALL = "SELECT aircraft_id, aircraft_producer, aircraft_model, aircraft_registration_code FROM aircompany_manager_db.aircrafts";
    String SQL_AIRCRAFTS_SELECT_BY_ID = "SELECT aircraft_id, aircraft_producer, aircraft_model, aircraft_registration_code FROM aircompany_manager_db.aircrafts where aircraft_id = ?";
    String SQL_AIRCRAFTS_INSERT = "INSERT INTO aircompany_manager_db.aircrafts (aircraft_producer, aircraft_model, aircraft_registration_code) VALUES (?,?,?)";
    String SQL_AIRCRAFTS_DELETE_BY_ID = "DELETE FROM aircompany_manager_db.aircrafts WHERE aircraft_id = ?";
    String SQL_AIRCRAFTS_UPDATE_BY_ID = "UPDATE aircompany_manager_db.aircrafts SET aircraft_producer = ?, aircraft_model = ?, aircraft_registration_code = ? WHERE aircraft_id = ?";

    //Airports sql
    String SQL_AIRPORTS_SELECT_ALL = "SELECT airport_id, name, country, city, iata_code FROM aircompany_manager_db.airports";
    String SQL_AIRPORTS_SELECT_BY_ID = "SELECT airport_id, name, country, city, iata_code FROM aircompany_manager_db.airports where airport_id = ?";
    String SQL_AIRPORTS_INSERT = "INSERT INTO aircompany_manager_db.airports (name, country, city, iata_code) VALUES (?,?,?,?)";
    String SQL_AIRPORTS_DELETE_BY_ID = "DELETE FROM aircompany_manager_db.airports WHERE airport_id = ?";
    String SQL_AIRPORTS_UPDATE_BY_ID = "UPDATE aircompany_manager_db.airports SET name = ?, country = ?, city = ?, iata_code = ? WHERE airport_id = ?;";

    //Brigade sql
    String SQL_BRIGADES_SELECT_ALL ="SELECT brigade_id, brigade_name FROM aircompany_manager_db.brigades";
    String SQL_BRIGADES_SELECT_BY_ID = "SELECT brigade_id, brigade_name FROM aircompany_manager_db.brigades WHERE brigade_id = ?";
    String SQL_BRIGADES_INSERT = "INSERT INTO aircompany_manager_db.brigades (brigade_name) VALUES (?)";
    String SQL_BRIGADES_DELETE_BY_ID = "DELETE FROM aircompany_manager_db.brigades WHERE brigade_id = ?";
    String SQL_BRIGADES_UPDATE_BY_ID = "UPDATE aircompany_manager_db.brigades SET brigade_name = ? WHERE brigade_id = ?";


    //Flights sql
    String SQL_FLIGHTS_SELECT_ALL = "SELECT flight_id, aircraft_id, brigade_id, departure_airport_id, destination_airport_id, flight_callsign, departure_datetime FROM aircompany_manager_db.flights";
    String SQL_FLIGHTS_SELECT_BY_ID = "SELECT flight_id, aircraft_id, brigade_id, departure_airport_id, destination_airport_id, flight_callsign, departure_datetime FROM aircompany_manager_db.flights where flight_id = ?";
    String SQL_FLIGHTS_INSERT = "INSERT INTO aircompany_manager_db.flights (aircraft_id, brigade_id, departure_airport_id, destination_airport_id, flight_callsign, departure_datetime) VALUES (?,?,?,?,?,?)";
    String SQL_FLIGHTS_DELETE_BY_ID = "DELETE FROM aircompany_manager_db.flights WHERE flight_id = ?";
    String SQL_FLIGHTS_UPDATE_BY_ID = "UPDATE aircompany_manager_db.flights SET aircraft_id = ?, brigade_id = ?, departure_airport = ?, destination_airport = ?, flight_callsign = ?, departure_datetime = ? WHERE flight_id = ?";

    //Role sql
    String SQL_ROLES_SELECT_ALL = "SELECT role_id, role_type FROM aircompany_manager_db.roles";
    String SQL_ROLES_SELECT_BY_ID = "SELECT role_id, role_type FROM aircompany_manager_db.roles WHERE role_id = ?";
    String SQL_ROLES_INSERT = "INSERT INTO aircompany_manager_db.roles (role_id, role_type) VALUES (?,?)";
    String SQL_ROLES_DELETE_BY_ID = "DELETE FROM aircompany_manager_db.roles WHERE role_id = ?";
    String SQL_ROLES_UPDATE_BY_ID = "UPDATE aircompany_manager_db.roles SET role_type = ? WHERE role_id =?";

    //User
    String SQL_USERS_SELECT_ALL = "SELECT user_id, role_id, first_name, last_name, email, username, password, salt FROM aircompany_manager_db.users";
    String SQL_USERS_SELECT_BY_ID = "SELECT user_id, role_id, first_name, last_name, email, username, password, salt FROM aircompany_manager_db.users WHERE user_id = ?";
    String SQL_USERS_INSERT = "INSERT INTO aircompany_manager_db.users (role_id, first_name, last_name, email, username, password, salt) VALUES (?,?,?,?,?,?,?)";
    String SQL_USERS_DELETE_BY_ID = "DELETE FROM aircompany_manager_db.users WHERE user_id = ?";
    String SQL_USERS_UPDATE_BY_ID = "UPDATE aircompany_manager_db.users SET role_id = ?, first_name = ?, last_name = ?, email = ?, username = ?, password = ?, salt = ? WHERE user_id = ?";
    String SQL_USERS_SELECT_BY_USERNAME = "SELECT user_id, role_id, first_name, last_name, email, username, password, salt FROM aircompany_manager_db.users WHERE username = ?";
    String SQL_USERS_UPDATE_ROLE = "UPDATE users SET role_id =? WHERE user_id = ?";

    String SQL_CHECK_FREEUSERNAME = "SELECT username FROM aircompany_manager_db.users WHERE username like ?";
    String SQL_CHECK_EMAIL = "SELECT email FROM aircompany_manager_db.users WHERE email like ?";

  //  String PROC = "{CALL aircompany_manager_db.add_new_user(?,?,?,?,?,?,?,?,?)}";
  //  String PROC2 = "{CALL aircompany_manager_db.fillFlight (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    //String SELECT_BRIGADES_USER = "select user_id, role_id, first_name, last_name FROM users as u INNER JOIN brigades_has_users as bhu on u.user_id = bhu.users_user_id INNER JOIN brigades as b on b.brigade_id = bhu.brigades_brigade_id where b.brigade_id = ?";
    String SELECT_BRIGADES_USER = "select  user_id, role_id, first_name, last_name FROM users as u LEFT JOIN brigades_has_users as bhu on u.user_id = bhu.users_user_id where brigades_brigade_id =  ?";
    String SELECT_BRIGADES_FREE_USER = "select user_id, role_id, first_name, last_name FROM users where user_id  not in (select users_user_id from brigades_has_users where brigades_brigade_id = ?)";

    String SQL_BRIGADE_HAS_USERS_INSERT = "INSERT INTO brigades_has_users (brigades_brigade_id, users_user_id) VALUES (?, ?)";
    String SQL_BRIGADE_HAS_USERS_DELETE = "DELETE FROM brigades_has_users WHERE brigades_brigade_id = ? and users_user_id = ?";
}
