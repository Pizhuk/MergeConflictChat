package com.mergemconflictchat.model;

import java.util.Objects;

public class User {

    private final String login;
    private final String email;
    private String password;
    private final String phoneNumber;
    private final String company;

    public User(String login, String email, String password, String phoneNumber, String company) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.company = company;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCompany() {
        return company;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", company='" + company + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(login, user.login) && Objects.equals(email, user.email)
                && Objects.equals(password, user.password) && Objects.equals(phoneNumber, user.phoneNumber)
                && Objects.equals(company, user.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, email, password, phoneNumber, company);
    }

}