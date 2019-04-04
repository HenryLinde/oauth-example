package com.example.oauthexample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExampleController {

    @RequestMapping(method = RequestMethod.GET, value = "/example/authorized")
    @PreAuthorize("#oauth2.hasScope('read')")
    public ResponseEntity<String> authorizedExample() {
        return new ResponseEntity("You have access", HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/example/noAuth")
    public ResponseEntity<String> noAuthExample() {
        return new ResponseEntity("You have access", HttpStatus.OK);
    }
}
