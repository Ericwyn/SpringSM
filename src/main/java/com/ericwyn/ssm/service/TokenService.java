package com.ericwyn.ssm.service;

import com.ericwyn.ssm.model.User;
import com.ericwyn.ssm.util.obj.AccessToken;

/**
 * Created by Ericwyn on 18-3-30.
 */
public interface TokenService {
    public void saveToken(final AccessToken token);
    public boolean isTokenExpired(AccessToken token);
    public AccessToken getToken(String token);
    public User getUserByToken(String token);
    public void removeToken(String token);
    public void startClearCheckCodeMap() ;

}
