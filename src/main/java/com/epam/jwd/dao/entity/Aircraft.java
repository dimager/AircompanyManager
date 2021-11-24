package com.epam.jwd.dao.entity;

import java.util.Objects;

public class Aircraft extends Entity {
    private int id;
    private String producer;
    private String model;
    private String registrationCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aircraft aircraft = (Aircraft) o;
        return id == aircraft.id && producer.equals(aircraft.producer) && model.equals(aircraft.model) && registrationCode.equals(aircraft.registrationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, producer, model, registrationCode);
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "id=" + id +
                ", producer='" + producer + '\'' +
                ", model='" + model + '\'' +
                ", registrationCode='" + registrationCode + '\'' +
                '}';
    }
}
