package com.br.project.Back.model.dto;

public class UserAuthDTO {

    private String email;
    private String password;
    private Boolean returnSecureToken = true;

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

    public Boolean getReturnSecureToken() {
        return returnSecureToken;
    }

    public void setReturnSecureToken(Boolean returnSecureToken) {
        this.returnSecureToken = returnSecureToken;
    }
}
