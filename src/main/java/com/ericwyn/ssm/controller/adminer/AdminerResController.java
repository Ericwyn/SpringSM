package com.ericwyn.ssm.controller.adminer;

import com.ericwyn.ssm.model.Adminer;
import com.ericwyn.ssm.service.impl.AdminerServerImpl;
import com.ericwyn.ssm.service.impl.TokenServiceImpl;
import com.ericwyn.ssm.util.ResJson;
import com.ericwyn.ssm.util.obj.AccessToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created by Ericwyn on 18-6-20.
 */
@Controller
@RequestMapping("/ad")
public class AdminerResController {

    @Autowired
    AdminerServerImpl adminerServer;

    @Autowired
    TokenServiceImpl tokenService;


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public ResJson login(@RequestParam("account") String account,
                         @RequestParam("password") String password){
        Adminer adminer = null;
        if ((adminer = adminerServer.login(account,password))!=null){
            AccessToken token = new AccessToken();
            token.setUser(adminer);
            tokenService.saveToken(token);
            HashMap<String,Object> res = new HashMap<>();
            res.put("token",token.getUuid());
            return ResJson.successJson("login success",res);
        }else {
            return ResJson.failJson("登录失败，用户不存在或者密码错误");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResJson getInfo(@RequestParam("token") String token){
        Adminer adminer = null;
        if ((adminer = (Adminer) tokenService.getUserByToken(token))!=null){
            HashMap<String,Object> res = new HashMap<>();
            res.put("user",adminer);
            return ResJson.successJson("get user info success",res);
        }else {
            return ResJson.tokenFailResJson();
        }
    }

    @ResponseBody
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public ResJson findAllAdminer(){
        return ResJson.successJson("success to find all adminer",adminerServer.findAll());
    }

}
