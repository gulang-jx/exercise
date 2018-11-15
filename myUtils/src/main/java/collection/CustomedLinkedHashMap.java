package collection;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 实现一个定长的有序的hashMap，通过在构造方法中传入size指定该对象存储数据的数量
 */
public class CustomedLinkedHashMap extends LinkedHashMap {
    private int size;
    public CustomedLinkedHashMap(){}
    public CustomedLinkedHashMap(int size){
        this.size = size;
    }
    public CustomedLinkedHashMap(int initialCapacity,float loadFactor){
        super(initialCapacity, loadFactor);
    }
    public CustomedLinkedHashMap(int initialCapacity,float loadFactor,int size){
        super(initialCapacity, loadFactor);
        this.size = size;
    }
    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size>size();
    }
}
