package collection;

import java.util.AbstractSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * 自定义有序定长set
 * @param <E>
 */
public class CustomedLinkedHashSet<E> extends AbstractSet<E>
        implements Set<E>, Cloneable, java.io.Serializable{
    static final long serialVersionUID = -5024744406713321676L;
    private transient HashMap<E,Object> map;
    private static final Object PRESENT = new Object();


    public CustomedLinkedHashSet(){
        map = new CustomedLinkedHashMap(16,.75f);
    }
    public CustomedLinkedHashSet(int size){
        map = new CustomedLinkedHashMap(size);
    }
    public CustomedLinkedHashSet(int initialCapacity,float loadFactor,int size){
        map = new CustomedLinkedHashMap(initialCapacity,loadFactor,size);
    }




    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }
    @Override
    public int size() {
        return map.size();
    }
    public boolean isEmpty(){
        return map.isEmpty();
    }
    public boolean contains(Object o){
        return map.containsKey(o);
    }
    public boolean add(E e){
        return map.put(e,PRESENT) == null;
    }
    public boolean remove(Object o){
        return map.remove(o)==PRESENT;
    }
    public void clear(){
        map.clear();
    }

}
