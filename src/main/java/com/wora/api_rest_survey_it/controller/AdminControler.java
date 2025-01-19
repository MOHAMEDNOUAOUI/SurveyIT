package com.wora.api_rest_survey_it.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminControler {

    @GetMapping()
    public String test() {
        return "Admin is here";
    }
}
