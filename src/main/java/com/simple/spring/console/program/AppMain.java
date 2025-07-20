package com.simple.spring.console.program;

import com.simple.spring.console.program.bean.Program;
import com.simple.spring.console.program.config.AppConfig;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        Program program = context.getBean(Program.class);
        program.startProgram();
        program.endProgram(context);
    }
}
