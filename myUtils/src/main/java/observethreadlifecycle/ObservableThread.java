package observethreadlifecycle;

public class ObservableThread<T> extends Thread implements Observable{
    private final TaskLifeCycle<T> taskLifeCycle;
    private final Task<T> task;
    private CycleEnum cycleEnum;

    public ObservableThread(Task<T> task){
        this(new TaskLifeCycle.EmptyTaskLifeCycle<T>(),task);
    }
    public ObservableThread(TaskLifeCycle<T> taskLifeCycle,Task<T> task){
        this.taskLifeCycle = taskLifeCycle;
        this.task = task;
    }
    @Override
    public CycleEnum getCycle() {
        return this.cycleEnum;
    }

    @Override
    public final void run() {
        this.update(CycleEnum.START,null,null);
        try{
            this.update(CycleEnum.RUNNING,null,null);
            T result = this.task.call();
            this.update(CycleEnum.FINISHED,result,null);
        }catch (Exception e){
            this.update(CycleEnum.ERROR,null,e);
        }
    }

    private void update(CycleEnum cycleEnum,T result,Exception e){
        this.cycleEnum = cycleEnum;
        if(taskLifeCycle == null) return ;
        try{
            switch (cycleEnum){
                case START:
                    this.taskLifeCycle.onStart(currentThread());
                    break;
                case RUNNING:
                    this.taskLifeCycle.onRunning(currentThread());
                    break;
                case FINISHED:
                    this.taskLifeCycle.onFinished(currentThread(),result);
                    break;
                case ERROR:
                    this.taskLifeCycle.onError(currentThread(),e);
            }
        }catch (Exception e1){
            if(this.cycleEnum == CycleEnum.ERROR) throw e1;
        }
    }
}
