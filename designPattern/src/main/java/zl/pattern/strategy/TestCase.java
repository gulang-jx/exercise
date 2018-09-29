package zl.pattern.strategy;

public class TestCase {
    public static void main(String[] args) {
        IMsgHandle iMsgHandle = new OnlineMessage();
        Message message = new Message(iMsgHandle);
        iMsgHandle.handle("hello world");

        iMsgHandle = new OnlineMessage();
        message = new Message(iMsgHandle);
        iMsgHandle.handle("hello world");
    }
}
