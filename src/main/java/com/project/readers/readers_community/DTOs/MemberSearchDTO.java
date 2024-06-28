package com.project.readers.readers_community.DTOs;

public class MemberSearchDTO
{
    private Integer id;
    private String fullName;
    private String email;
    private String profilePhotoURL;

    public MemberSearchDTO(Integer id, String fullName, String email, String profilePhotoURL) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.profilePhotoURL = profilePhotoURL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePhotoURL() {
        return profilePhotoURL;
    }

    public void setProfilePhotoURL(String profilePhotoURL) {
        this.profilePhotoURL = profilePhotoURL;
    }

    @Override
    public String toString() {
        return "MemberSearchDTO{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", profilePhotoURL='" + profilePhotoURL + '\'' +
                '}';
    }
}
