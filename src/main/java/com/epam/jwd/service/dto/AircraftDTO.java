package com.epam.jwd.service.dto;

import java.util.Objects;

public class AircraftDTO extends BaseDTO {
    private int aircraftId;
    private String producer;
    private String model;
    private String registrationCode;

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }

    public void setAircraftId(int aircraftId) {
        this.aircraftId = aircraftId;
    }

    public int getAircraftId() {
        return aircraftId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AircraftDTO that = (AircraftDTO) o;
        return aircraftId == that.aircraftId && producer.equals(that.producer) && model.equals(that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aircraftId, producer, model);
    }

    @Override
    public String toString() {
        return "AircraftDTO{" +
                "aircraftId=" + aircraftId +
                ", producer='" + producer + '\'' +
                ", model='" + model + '\'' +
                ", registrationCode='" + registrationCode + '\'' +
                '}';
    }
}
