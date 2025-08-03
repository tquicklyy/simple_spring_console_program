package com.spring.boot.console_program;

import com.spring.boot.console_program.bean.function.Calculator;
import com.spring.boot.console_program.bean.function.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
class ConsoleProgramApplicationTests {

	@Autowired
	State state;

	@Autowired
	Calculator calculator;

	@BeforeEach
	void setUp() {
		state.calculator();
		calculator.mul(0);
	}

	@Test
	void whenSumFiveThenScoreIncreaseByFive() {
		double score = calculator.getScore();
		calculator.sum(5);
		Assertions.assertEquals(score + 5, calculator.getScore());
	}

	@Test
	void whenSubFiveThenScoreDecreaseByFive() {
		double score = calculator.getScore();
		calculator.sub(5);
		Assertions.assertEquals(score - 5, calculator.getScore());
	}

	@Test
	void whenMulFiveThenScoreIncreaseByFiveTimes() {
		double score = calculator.getScore();
		calculator.mul(5);
		Assertions.assertEquals(score * 5, calculator.getScore());
	}

	@Test
	void whenDivFiveThenScoreDecreaseByFiveTimes() {
		double score = calculator.getScore();
		calculator.div(5);
		Assertions.assertEquals(score / 5, calculator.getScore());
	}
}
