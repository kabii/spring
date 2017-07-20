package tobi.ch3.test;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {
	private Calculator calculator;
	private String filepath;

	@Before
	public void setUp() {
		calculator = new Calculator();
		filepath = new File("src/test/resources/numbers.txt").getAbsolutePath();
	}

	@Test
	public void sumOfNumbers() throws IOException {
		int sum = calculator.sum(filepath);
		assertThat(sum, is(10));
	}

	@Test
	public void multiplyOfNumbers() throws IOException {
		int multiply = calculator.multiply(filepath);
		assertThat(multiply, is(24));
	}
}