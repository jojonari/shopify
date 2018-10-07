package com.fast87.shopify.controller;

import com.fast87.shopify.dto.Auth;
import com.fast87.shopify.service.AppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/app")
public class AppController {

    @Autowired
    AppService service;

    @GetMapping("/auth")
    public String auth(HttpServletRequest request, Auth auth) throws Exception {
        log.info("app/auth?{}", request.getQueryString());
        log.info("auth : {}", auth.toString());

        String hmac = service.compareHmac(request.getQueryString());

        if (!hmac.equals(auth.getHmac()))
            return "error";


        return service.makeRedirectUrl(auth.getShop());
    }

}
