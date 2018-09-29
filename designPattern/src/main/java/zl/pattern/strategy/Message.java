package zl.pattern.strategy;


public class Message {
    private IMsgHandle iMsgHandle;
    public Message(IMsgHandle iMsgHandle){
        this.iMsgHandle = iMsgHandle;
    }
    public void doHandle(String message){
        iMsgHandle.handle(message);
    }
}
