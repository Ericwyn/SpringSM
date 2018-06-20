package com.ericwyn.ssm.service.impl;

import com.ericwyn.ssm.model.User;
import com.ericwyn.ssm.service.TokenService;
import com.ericwyn.ssm.util.obj.AccessToken;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ericwyn on 18-3-30.
 */
@Service
public class TokenServiceImpl implements TokenService {
    private HashMap<String,AccessToken> tokenMap = new HashMap<>();
    private static final int CLEAN_MAP_TIME = 60;   //清理过期accessToken的时间间隔，单位为分钟
    // token 的过期时间在AccessToken类里面设置

    private boolean startCleanThreadFlag = false;

    @Override
    public void saveToken(final AccessToken token) {
        startClearCheckCodeMap();
        tokenMap.put(token.getUuid(),token);
        //废弃旧的 user token

        //暂时不启用，如果短时间登录人数过多的话，将会导致短时间内开启多个进程，会gg的
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Set<String> keys = tokenMap.keySet();
//                for (String key:keys){
//                    if (tokenMap.get(key).getUser().equals(token.getUser())){
//                        removeToken(key);
//                    }
//                }
//            }
//        });
    }

    @Override
    public boolean isTokenExpired(AccessToken token) {
        return token.isExpired();
    }

    @Override
    public AccessToken getToken(String token) {
        return tokenMap.get(token);
    }

    public User getUserByToken(String token){
        AccessToken tokenTemp = null;
        if((tokenTemp=getToken(token))!=null){
            if(tokenTemp.isExpired()){
                return null;
            }else {
                return tokenTemp.getUser();
            }
        }else {
            return null;
        }
    }

    public void removeToken(String token){
        tokenMap.remove(token);
    }

    public void startClearCheckCodeMap() {
        if (!startCleanThreadFlag){
            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            executor.scheduleAtFixedRate(
                    new Runnable() {
                        @Override
                        public void run() {
                            Set keys = tokenMap.keySet();
                            for (Object key:keys){
                                if(tokenMap.get(key).isExpired()){
                                    tokenMap.remove(key);
                                }
                            }
                        }
                    },
                    CLEAN_MAP_TIME,
                    CLEAN_MAP_TIME,
                    TimeUnit.MINUTES);
            startCleanThreadFlag = true;
        }
    }
}

