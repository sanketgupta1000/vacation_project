package com.project.readers.readers_community.DTOs;

public class MemberSearchDTO
{

    private Integer id;
    private String fullName;
    private String email;

    public MemberSearchDTO(Integer id, String fullName, String email)
    {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    // no args
    public MemberSearchDTO()
    {
    }

    // all getters and setters


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

    // toString
    @Override
    public String toString()
    {
        return "MemberSearchDTO{" + "id=" + id + ", fullName='" + fullName + '\'' + ", email='" + email + '\'' + '}';
    }
}
