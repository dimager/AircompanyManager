package com.epam.jwd.view;

import com.epam.jwd.dao.BaseDao;
import com.epam.jwd.dao.impl.BrigadeDaoImpl;
import com.epam.jwd.dao.impl.BrigadeWithUsersDaoImpl;
import com.epam.jwd.dao.impl.FlightDaoImpl;
import com.epam.jwd.entity.Aircraft;
import com.epam.jwd.dao.impl.AircraftDaoImpl;
import com.epam.jwd.entity.Brigade;

import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException, InterruptedException {

    BrigadeDaoImpl brigadeDao = new BrigadeDaoImpl();
        BrigadeWithUsersDaoImpl brigadeWithUsersDao = new BrigadeWithUsersDaoImpl();
        System.out.println(brigadeWithUsersDao.findById(brigadeDao.findById(1L)));

    }

}
