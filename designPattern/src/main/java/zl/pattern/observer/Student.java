package zl.pattern.observer;

import java.util.Observable;
import java.util.Observer;

public class Student implements Observer {
    private String name;
    public Student(Observable observable ,String name){
        observable.addObserver(this);
        this.name = name;
    }
    @Override
    public void update(Observable o, Object arg) {
        Teacher t = (Teacher)o;
        System.out.println(this.name+"同学--"+t.getHomeWork());
    }
}
