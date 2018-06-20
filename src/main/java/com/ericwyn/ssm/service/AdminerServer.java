package com.ericwyn.ssm.service;

import com.ericwyn.ssm.model.Adminer;

import java.util.List;

/**
 * Created by Ericwyn on 18-6-20.
 */
public interface AdminerServer {
    public Adminer login(String account,String password);
    void insert(Adminer adminer);
    List<Adminer> findAll();
}
