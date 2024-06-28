package com.project.readers.readers_community.DTOs;

import com.project.readers.readers_community.enums.UserType;

public class AuthDTO
{
    private int id;
    private UserType userType;
    private String profilePhotoURL;

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

    public String getProfilePhotoURL() {
        return profilePhotoURL;
    }

    public void setProfilePhotoURL(String profilePhotoURL) {
        this.profilePhotoURL = profilePhotoURL;
    }

    public AuthDTO(int id, UserType userType, String profilePhotoURL) {
        this.id = id;
        this.userType = userType;
        this.profilePhotoURL = profilePhotoURL;
    }

    @Override
    public String toString() {
        return "AuthDTO{" +
                "id=" + id +
                ", userType=" + userType +
                ", profilePhotoURL='" + profilePhotoURL + '\'' +
                '}';
    }
}
