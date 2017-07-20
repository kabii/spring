package tobi.ch3.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
	public int sum(String filepath) throws IOException {
		LineCallback callback = (line, value) -> {
			value += Integer.parseInt(line);
			return value;
		};
		return lineReadTemplate(filepath, callback, 0);
	}

	public int multiply(String filepath) throws IOException {
		LineCallback callback = (line, value) -> {
			value *= Integer.parseInt(line);
			return value;
		};
		return lineReadTemplate(filepath, callback, 1);
	}

	public int fileReadTemplate(String filepath, BufferedReaderCallback callback) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader((filepath)))) {
			return callback.doSomethingWithReader(br);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	public int lineReadTemplate(String filepath, LineCallback callback, int initValue) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
			String line;
			int value = initValue;
			while ((line = br.readLine()) != null) {
				value = callback.doSomethingWithLine(line, value);
			}
			return value;
		}
	}
}
