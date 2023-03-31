package com.sc.caselist.logic;

import com.sc.caselist.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImageLogic {

    @Autowired
    private ImageMapper imageMapper;
}
