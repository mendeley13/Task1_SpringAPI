package me.i.springapi.api.model;

public class PostInput {


    private String login;
    private String password;

    public PostInput(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }
}
