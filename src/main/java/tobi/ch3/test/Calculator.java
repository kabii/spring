package tobi.ch3.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
	public int sum(String filepath) throws IOException {
		LineCallback<Integer> callback = (line, value) -> {
			value += Integer.parseInt(line);
			return value;
		};
		return lineReadTemplate(filepath, callback, 0);
	}

	public int multiply(String filepath) throws IOException {
		LineCallback<Integer> callback = (line, value) -> {
			value *= Integer.parseInt(line);
			return value;
		};
		return lineReadTemplate(filepath, callback, 1);
	}

	public String concatenate(String filepath) throws IOException {
		LineCallback<String> callback = (line, value) -> value + line;
		return lineReadTemplate(filepath, callback, "");
	}

	public int fileReadTemplate(String filepath, BufferedReaderCallback callback) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader((filepath)))) {
			return callback.doSomethingWithReader(br);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	public <T> T lineReadTemplate(String filepath, LineCallback<T> callback, T initValue) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
			String line;
			T value = initValue;
			while ((line = br.readLine()) != null) {
				value = callback.doSomethingWithLine(line, value);
			}
			return value;
		}
	}
}
