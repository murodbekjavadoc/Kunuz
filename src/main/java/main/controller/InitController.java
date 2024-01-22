package main.controller;

import main.servise.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/init")
public class InitController {
    @Autowired
    private InitService initService;
    public String initAdmin(){
        initService.initAdmin();
        return "DONE";
    }

}
