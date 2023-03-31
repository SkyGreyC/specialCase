package com.sc.caselist.service;

import com.sc.caselist.logic.ImageLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/image")
public class ImageService {

    @Autowired
    private ImageLogic imageLogic;
}
