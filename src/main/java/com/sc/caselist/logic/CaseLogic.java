package com.sc.caselist.logic;

import com.sc.caselist.mapper.CaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CaseLogic {

    @Autowired
    private CaseMapper CaseMapper;
}
