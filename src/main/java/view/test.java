package view;

import repository.entity.Aircraft;
import repository.impl.AircraftRepositoryImpl;

import java.util.List;

public class test extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        AircraftRepositoryImpl aircraftRepository = new AircraftRepositoryImpl();
        List<Aircraft> list = aircraftRepository.findAll();
        list.stream().forEach(System.out::println);
       /* try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
