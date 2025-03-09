package me.i.springapi.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public class User {
    @NotBlank
    @NotNull
    private String login;
    @NotBlank
    @NotNull
    private String password;
    private String email;
    private Timestamp date;


    public User(String login, String password, String status, String email, Timestamp date) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.date = date;
    }

    public User() {
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

    public Timestamp getDate() {
        return date;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
