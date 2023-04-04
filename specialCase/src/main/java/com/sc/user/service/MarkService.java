package com.sc.user.service;

import com.sc.user.logic.MarkLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/mark")
public class MarkService {

    @Autowired
    private MarkLogic markLogic;
}
