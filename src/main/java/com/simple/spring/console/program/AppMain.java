package com.simple.spring.console.program;

import com.simple.spring.console.program.bean.Program;
import com.simple.spring.console.program.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppMain {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        Program program = context.getBean(Program.class);
        program.startProgram();
    }
}
