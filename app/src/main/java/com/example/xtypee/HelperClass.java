package com.example.xtypee;

public class HelperClass {

    String name, email, usernameusuariousuarios, contra;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsernameusuariousuarios() {
        return usernameusuariousuarios;
    }

    public void setUsernameusuariousuarios(String usernameusuariousuarios) {
        this.usernameusuariousuarios = usernameusuariousuarios;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public HelperClass(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.usernameusuariousuarios = username;
        this.contra = password;
    }

    public HelperClass() {
    }
}
