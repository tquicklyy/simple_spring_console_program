package com.simple.spring.console.program.bean.aspect;

import com.simple.spring.console.program.utils.ThreadUtils;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ContextClosedAspect {

    private static final Logger LOG = LoggerFactory.getLogger(ContextClosedAspect.class);

    @Pointcut("execution(void org.springframework.context.ConfigurableApplicationContext.close())")
    public void contextClosePointCut() {}

    @Before("contextClosePointCut()")
    public void beforeContextClose() throws InterruptedException {
        LOG.info("Starting context closing!");
        Thread.sleep(ThreadUtils.TIME_TO_SLEEP);
    }

    @After("contextClosePointCut()")
    public void afterContextClose() throws InterruptedException {
        LOG.info("Context is closed. Goodbye!");
    }
}
