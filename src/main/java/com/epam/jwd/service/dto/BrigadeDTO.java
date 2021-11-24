package com.epam.jwd.service.dto;

import java.util.Objects;

public class BrigadeDTO extends BaseDTO {
    private long brigadeId;
    private String brigadeName;

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
        return brigadeId == that.brigadeId && brigadeName.equals(that.brigadeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brigadeId, brigadeName);
    }

    @Override
    public String toString() {
        return "BrigadeDTO{" +
                "brigadeId=" + brigadeId +
                ", brigadeName='" + brigadeName + '\'' +
                '}';
    }
}
