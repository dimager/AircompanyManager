package com.epam.jwd.view;

import com.epam.jwd.entity.Aircraft;
import com.epam.jwd.dao.impl.AircraftDaoImpl;

import java.util.List;

public class test extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        AircraftDaoImpl aircraftRepository = new AircraftDaoImpl();
        List<Aircraft> list = aircraftRepository.findAll();
        list.stream().forEach(System.out::println);
       /* try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
