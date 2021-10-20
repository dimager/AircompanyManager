package view;

import repository.entity.Aircraft;
import repository.entity.User;
import repository.impl.AircraftRepositoryImpl;
import repository.impl.UserRepositoryImpl;

import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException, InterruptedException {
        AircraftRepositoryImpl aircraft = new AircraftRepositoryImpl();
        Aircraft aircraf2t = new Aircraft();
        System.out.println(aircraf2t);
    }
}
