package com.fincare.user.entity;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "CustomerUpdate",url = "https://fisesb.fincarebank.com")
public interface CustomerUpdateProxy {

    @PostMapping("/bankservices/process/")
    public String updateDetails(@RequestBody String xml);
}
