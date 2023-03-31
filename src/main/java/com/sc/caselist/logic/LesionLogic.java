package com.sc.caselist.logic;

import com.sc.caselist.mapper.LesionMapper;
import com.sc.home.mapper.HomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LesionLogic {

    @Autowired
    private LesionMapper lesionMapper;
}
