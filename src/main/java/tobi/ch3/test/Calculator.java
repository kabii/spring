package tobi.ch3.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
	public int sum(String filepath) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
			int sum = 0;
			String line;
			while ((line = br.readLine()) != null) {
				sum += Integer.parseInt(line);
			}
			br.close();
			return sum;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
}
