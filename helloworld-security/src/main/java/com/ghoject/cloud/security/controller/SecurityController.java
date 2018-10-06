package com.ghoject.cloud.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("doc")
public class SecurityController {

    @RequestMapping("api/getApi")
    public String getApi() {
        return "<h2>return api list</h2>";
    }

    @RequestMapping("doc/getDoc")
    public String getDoc() {
        return "<h2>return doc list</h2>";
    }
}
