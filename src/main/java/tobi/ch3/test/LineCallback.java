package tobi.ch3.test;

public interface LineCallback<T> {
	T doSomethingWithLine(String line, T value);
}
