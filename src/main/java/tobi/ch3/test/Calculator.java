package tobi.ch3.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
	public int sum(String filepath) throws IOException {
		return fileReadTemplate(filepath, br -> {
			int sum = 0;
			String line;
			while ((line = br.readLine()) != null) {
				sum += Integer.parseInt(line);
			}
			return sum;
		});
	}

	public int multiply(String filepath) throws IOException {
		return fileReadTemplate(filepath, br -> {
			int multiply = 1;
			String line;
			while ((line = br.readLine()) != null) {
				multiply *= Integer.parseInt(line);
			}
			return multiply;
		});
	}

	public int fileReadTemplate(String filepath, BufferedReaderCallback callback) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader((filepath)))) {
			return callback.doSomethingWithReader(br);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
}
