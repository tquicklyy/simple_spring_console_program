package com.spring.boot.console_program.bean;

import com.spring.boot.console_program.bean.function.WorkTime;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class ConsolePromptProvider implements PromptProvider {

    private static final Logger LOG = LoggerFactory.getLogger(ConsolePromptProvider.class);
    private final WorkTime workTime;

    public ConsolePromptProvider(WorkTime workTime) {
        this.workTime = workTime;
    }

    @Override
    public AttributedString getPrompt() {
        workTime.printTimeOfWork();
        return new AttributedString("shell:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN));
    }
}
