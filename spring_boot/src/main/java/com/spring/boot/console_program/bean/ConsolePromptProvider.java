package com.spring.boot.console_program.bean;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class ConsolePromptProvider implements PromptProvider {

    private static final Logger LOG = LoggerFactory.getLogger(ConsolePromptProvider.class);

    @Override
    public AttributedString getPrompt() {
        return new AttributedString("_shell_:>", AttributedStyle.BOLD.foreground(AttributedStyle.GREEN));
    }
}
