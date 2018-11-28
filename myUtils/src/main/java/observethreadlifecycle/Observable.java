package observethreadlifecycle;

public interface Observable {
    enum CycleEnum{
        START,RUNNING,FINISHED,ERROR;
    }
    CycleEnum getCycle();

    void interrupt();
}
