package zl.pattern.observer;

import java.util.Observable;

public class Teacher extends Observable {
    private String homeWork;

    public void setHomeWork(String homeWork){
        this.homeWork = homeWork;
        setChanged();
        notifyObservers();
    }

    public String getHomeWork(){
        return "今天的作业是："+this.homeWork;
    }
}
