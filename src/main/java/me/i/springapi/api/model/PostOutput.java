package me.i.springapi.api.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PostOutput {


    private String login;
    private String password;
    private String date;

    public PostOutput(String login, String password, LocalDateTime date) {
        this.login = login;
        this.password = password;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date = dtf.format(date);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getDate() {
        return date;
    }
}
