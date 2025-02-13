package me.i.springapi.api.model;

public class GetOutput {
    private String login;
    private String status;

    public GetOutput(String login, String status) {
        this.login = login;
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public String getStatus() {
        return status;
    }
}
