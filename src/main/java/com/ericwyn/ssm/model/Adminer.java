package com.ericwyn.ssm.model;

/**
 * Created by Ericwyn on 18-6-19.
 */
public class Adminer implements User{
    private Integer id;
    private String account;
    private String password;

    public Adminer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
