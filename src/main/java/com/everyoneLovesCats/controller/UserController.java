package com.everyoneLovesCats.controller;

import com.everyoneLovesCats.service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    private ServiceUser serviceUser;

    @Autowired
    public void setUserService(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }



}
