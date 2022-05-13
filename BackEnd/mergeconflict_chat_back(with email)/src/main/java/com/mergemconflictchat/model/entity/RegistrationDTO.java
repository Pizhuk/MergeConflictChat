package com.mergemconflictchat.model.entity;

public class RegistrationDTO {

    private String login;
    private String email;
    private String password;
    private String phoneNumber;
    private String company;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String login, String password, String email, String phoneNumber, String company) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.company = company;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCompany() {
        return company;
    }

    @Override
    public String toString() {
        return "RegistrationDTO{" +
                "login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
