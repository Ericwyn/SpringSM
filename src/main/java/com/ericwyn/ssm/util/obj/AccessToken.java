package com.ericwyn.ssm.util.obj;

import com.ericwyn.ssm.model.User;

import java.util.UUID;

/**
 *
 * 用户token对象可以
 *
 * TODO：直接将token存储到数据库，这样的话当系统重启的时候已经登录的用户无需再次登录
 *
 * Created by Ericwyn on 18-1-12.
 */
public class AccessToken {
    //token 有效期 60 分钟
    private static final int MAX_EXPIRED_TIME = 60;

    //唯一编码
    private String uuid;
    //用户对象
    private User user;
    //时间戳
    private Long createTime;

    public AccessToken() {
        this.uuid= UUID.randomUUID().toString().replaceAll("-","");
        this.createTime=System.currentTimeMillis();
    }

    public String getUuid() {
        return uuid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //判断验证码是否过期，过期了返回true
    public boolean isExpired(){
        return (System.currentTimeMillis()-createTime)>1000 * 60 * MAX_EXPIRED_TIME;
    }

}
