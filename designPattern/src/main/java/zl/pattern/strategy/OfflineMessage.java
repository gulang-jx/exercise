package zl.pattern.strategy;

public class OfflineMessage implements IMsgHandle {
    @Override
    public void handle(String message) {
        System.out.println("offlinemessage start handle msg:"+message);
    }
}
