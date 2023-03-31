package com.sc.caselist.service;

import com.sc.caselist.logic.LesionLogic;
import com.sc.home.logic.HomeLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/lesion")
public class LesionService {

    @Autowired
    private LesionLogic lesionLogic;
}
