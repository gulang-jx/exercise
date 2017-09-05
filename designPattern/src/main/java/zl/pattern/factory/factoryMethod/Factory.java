package zl.pattern.factory.factoryMethod;

public abstract class Factory {
	public abstract <T extends Person>T getInstance(Class<T> c);
}
