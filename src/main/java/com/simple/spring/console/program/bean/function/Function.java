package com.simple.spring.console.program.bean.function;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public interface Function {

    @PostConstruct
    void postConstruct();

    @PreDestroy
    void preDestroy();

    void getOptions();

}
