package observethreadlifecycle;

public interface TaskLifeCycle<T> {

    void onStart(Thread thread);

    void onRunning(Thread thread);

    void onFinished(Thread thread ,T result);

    void onError(Thread thread,Exception e);


    class EmptyTaskLifeCycle<T> implements  TaskLifeCycle{
        @Override
        public void onStart(Thread thread) {

        }

        @Override
        public void onRunning(Thread thread) {

        }

        @Override
        public void onFinished(Thread thread, Object result) {

        }

        @Override
        public void onError(Thread thread, Exception e) {

        }
    }
}
