package com.fincare.user.controller;

import com.fincare.user.entity.UserEntity;
import com.fincare.user.service.CustomerUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Controller {

    @Autowired
    private CustomerUpdateService customerUpdateService;

    @PostMapping("/update")
    public Map<String,String> updateDetails(@RequestBody UserEntity userEntity)
    {
        Map<String,String> map=customerUpdateService.getDetails(userEntity);
        return map;
    }
}
