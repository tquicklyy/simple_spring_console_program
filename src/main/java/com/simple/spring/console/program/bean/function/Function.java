package com.simple.spring.console.program.bean.function;

import jakarta.annotation.PostConstruct;

public interface Function {

    @PostConstruct
    void postConstruct();

    void getOptions();

}
