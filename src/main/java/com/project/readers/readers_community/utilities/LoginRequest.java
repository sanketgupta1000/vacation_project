package com.project.readers.readers_community.utilities;

public class LoginRequest
{
    private String email;
    private String password;

    // constructor
    public LoginRequest(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public LoginRequest()
    {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
