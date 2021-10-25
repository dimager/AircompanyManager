package com.epam.jwd.entity;

import java.util.ArrayList;

public class BrigadeWithUsers extends Brigade{
    private ArrayList<User> brigadeUsers =  new ArrayList<>();

    public ArrayList<User> getBrigadeUsers() {
        return brigadeUsers;
    }

    public void setBrigadeUsers(ArrayList<User> brigadeUsers) {
        this.brigadeUsers = brigadeUsers;
    }

    @Override
    public String toString() {
        return super.toString() + "BrigadeWithUsers{" +
                "brigadeUsers=" + brigadeUsers +
                '}';
    }
}
