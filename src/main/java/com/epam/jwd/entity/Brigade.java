package com.epam.jwd.entity;


public class Brigade implements Entity {
    private long id;
    private String brigadeName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrigadeName() {
        return brigadeName;
    }

    public void setBrigadeName(String brigadeName) {
        this.brigadeName = brigadeName;
    }

    @Override
    public String toString() {
        return "Brigade{" +
                "id=" + id +
                ", brigadeName='" + brigadeName + '\'' +
                '}';
    }
}
