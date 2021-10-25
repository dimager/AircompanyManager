package com.epam.jwd.entity;

public enum Role implements Entity{
    ADMIN(1),MANAGER(2),PILOT(3),ENGINEER(4),FLIGHT_ATTENDANT(5);
    private long roleId;
    Role(long id) {
        this.roleId = id;
    }
    public long getRoleId() {
        return roleId;
    }
}
