package tobi.ch3.test;

import java.io.BufferedReader;
import java.io.IOException;

public interface BufferedReaderCallback {
	int doSomethingWithReader(BufferedReader reader) throws IOException;
}
