package com.epam.jwd.dao.entity;


import java.util.Objects;

public class Brigade extends Entity {
    private long id;
    private String brigadeName;
    private boolean isArchived;

    public boolean getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(boolean archived) {
        isArchived = archived;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brigade brigade = (Brigade) o;
        return id == brigade.id && isArchived == brigade.isArchived && Objects.equals(brigadeName, brigade.brigadeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brigadeName, isArchived);
    }

    @Override
    public String toString() {
        return "Brigade{" +
                "id=" + id +
                ", brigadeName='" + brigadeName + '\'' +
                ", isArchived=" + isArchived +
                '}';
    }
}
