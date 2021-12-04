package com.epam.jwd.dao.entity;

public enum Role {
    ADMIN(1), MANAGER(2), PILOT(3), RADIO_ENGINEER(4), FLIGHT_ATTENDANT(5), NAVIGATOR(6), GUEST(7);
    private int roleId;
    Role(int id) {
        this.roleId = id;
    }
    public int getRoleId() {
        return roleId;
    }

    public static Role getRoleName(int id){
        for(Role role : values()) {
            if(role.getRoleId() == id)
                return role;
        }
        return GUEST;
    }
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
