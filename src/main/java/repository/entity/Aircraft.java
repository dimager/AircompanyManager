package repository.entity;

public class Aircraft implements Entity {
    private long id;
    private String producer;
    private String model;

    public Aircraft() {
    }

    public Aircraft(String producer, String model) {
        this.producer = producer;
        this.model = model;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    @Override
    public String toString() {
        return "Aircraft{" +
                "id=" + id +
                ", producer='" + producer + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
