package com.ericwyn.ssm.service.impl;

import com.ericwyn.ssm.dao.AdminerDao;
import com.ericwyn.ssm.model.Adminer;
import com.ericwyn.ssm.service.AdminerServer;
import com.ericwyn.ssm.util.obj.AccessToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ericwyn on 18-6-20.
 */
@Service
public class AdminerServerImpl implements AdminerServer {
    @Autowired
    AdminerDao adminerDao;

    @Override
    public Adminer login(String account,String password){
        Adminer adminer = null;
        if ((adminer = adminerDao.findAdminerByAccount(account)) == null){
            return null;
        }else {
            if (password.endsWith(adminer.getPassword())){
                AccessToken accessToken = new AccessToken();
                return adminer;
            }else {
                return null;
            }
        }
    }

    @Override
    public void insert(Adminer adminer) {
        adminerDao.insert(adminer);
    }

    @Override
    public List<Adminer> findAll() {
        return adminerDao.findAll();
    }
}
