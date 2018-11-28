package observethreadlifecycle;

import java.util.concurrent.TimeUnit;

public class TestCase {
    public static void main(String[] args) {
        final TaskLifeCycle taskLifeCycle = new TaskLifeCycle.EmptyTaskLifeCycle<String>(){
            @Override
            public void onStart(Thread thread) {
                System.out.println("Thread start....");
            }

            @Override
            public void onRunning(Thread thread) {
                System.out.println("Thread is running...");
            }

            @Override
            public void onFinished(Thread thread, Object result) {
                System.out.println("Thread is finished and result is:"+result);
            }

            @Override
            public void onError(Thread thread, Exception e) {
                System.out.printf("Thread occur error{%s}",e);
            }
        };

        Observable observable = new ObservableThread<String>(taskLifeCycle,()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("finish done");
            return "Hello Thread!!!";
        });
        ((ObservableThread) observable).start();
    }
}
