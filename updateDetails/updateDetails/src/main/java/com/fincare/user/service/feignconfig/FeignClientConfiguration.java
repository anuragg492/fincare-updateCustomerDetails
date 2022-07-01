package com.fincare.user.service.feignconfig;

import feign.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class FeignClientConfiguration {

    @Bean
    public Client client() throws NoSuchAlgorithmException,
            KeyManagementException {

        return new Client.Default(
                new NaiveSSLSocketFactory("10.41.95.101","fisesb.fincarebank.com","localhost","fincareapis.fincarebank.com","uat-apps01.fincarebank.in"),
                new NaiveHostnameVerifier("10.41.95.101","fisesb.fincarebank.com","localhost","fincareapis.fincarebank.com","uat-apps01.fincarebank.in"));
    }
}
