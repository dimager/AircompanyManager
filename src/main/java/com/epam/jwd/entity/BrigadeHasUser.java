package com.epam.jwd.entity;

public class BrigadeHasUser implements Entity {
    long userId;
    long brigadeId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBrigadeId() {
        return brigadeId;
    }

    public void setBrigadeId(long brigadeId) {
        this.brigadeId = brigadeId;
    }
}
