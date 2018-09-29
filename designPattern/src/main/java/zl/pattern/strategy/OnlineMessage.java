package zl.pattern.strategy;

public class OnlineMessage implements IMsgHandle {
    @Override
    public void handle(String message) {
        System.out.println("OnlineMessage start handle message:"+message);
    }
}
