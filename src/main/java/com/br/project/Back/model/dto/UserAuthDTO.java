package com.br.project.Back.model.dto;

public class UserAuthDTO {

    private String email;
    private String password;

    public UserAuthDTO(){}

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
}
