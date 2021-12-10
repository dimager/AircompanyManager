package com.epam.jwd.service.dto;

import java.util.Objects;

public class BrigadeDTO extends BaseDTO {
    private long brigadeId;
    private String brigadeName;
    private boolean isArchived;

    public boolean getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(boolean archived) {
        isArchived = archived;
    }

    public long getBrigadeId() {
        return brigadeId;
    }

    public void setBrigadeId(long brigadeId) {
        this.brigadeId = brigadeId;
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
        BrigadeDTO that = (BrigadeDTO) o;
        return brigadeId == that.brigadeId && Objects.equals(brigadeName, that.brigadeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brigadeId, brigadeName, isArchived);
    }

    @Override
    public String toString() {
        return "BrigadeDTO{" +
                "brigadeId=" + brigadeId +
                ", brigadeName='" + brigadeName + '\'' +
                ", isArchived=" + isArchived +
                '}';
    }
}
