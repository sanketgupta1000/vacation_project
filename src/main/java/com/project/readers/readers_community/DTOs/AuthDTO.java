package com.project.readers.readers_community.DTOs;

import com.project.readers.readers_community.enums.UserType;

public class AuthDTO
{
    private int id;
    private UserType userType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public AuthDTO(int id, UserType userType) {
        this.id = id;
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "AuthDTO{" +
                "id=" + id +
                ", userType=" + userType +
                '}';
    }
}
