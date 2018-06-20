package com.ericwyn.ssm.dao;

import com.ericwyn.ssm.model.Adminer;

import java.util.List;

/**
 * Created by Ericwyn on 18-6-19.
 */
public interface AdminerDao {
    public Adminer findAdminerByAccount(String adminAccount);

    void insert(Adminer adminer);
    List<Adminer> findAll();
}
