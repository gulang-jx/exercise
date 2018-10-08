package zl.pattern.iterator;

/**
 *  点名接口类
 */
public interface Iterator {

    void first();    //第一个

    void next();            //下一个

    boolean isDone();       //是否点名完毕

    Object currentItems();  //当前同学的出勤情况
}
