package observethreadlifecycle;

@FunctionalInterface
public interface Task<T> {
    T call();
}
