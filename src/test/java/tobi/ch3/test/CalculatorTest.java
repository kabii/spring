package tobi.ch3.test;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {
	@Test
	public void sumOfNumbers() throws IOException {
		Calculator calculator = new Calculator();
		int sum = calculator.sum(new File("src/test/resources/numbers.txt").getAbsolutePath());
		assertThat(sum, is(10));
	}
}