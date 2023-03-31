package com.sc.caselist.service;

import com.sc.caselist.logic.CaseLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/case")
public class CaseService {

    @Autowired
    private CaseLogic CaseLogic;
}
