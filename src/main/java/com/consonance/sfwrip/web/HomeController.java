package com.consonance.sfwrip.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String test(){
        return "success";
    }

}

