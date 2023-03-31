package com.sc.user.logic;

import com.sc.user.mapper.MarkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MarkLogic {

    @Autowired
    private MarkMapper markMapper;
}
