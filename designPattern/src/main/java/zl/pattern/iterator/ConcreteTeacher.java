package zl.pattern.iterator;

public class ConcreteTeacher implements Teacher {
    private Object[] present = {"张三来了","李四来了","王五没来"};
    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(this);
    }

    public Object getElement(int index){
        if(index < present.length){
            return  present[index];
        }
        return null;
    }

    public int getSize(){
        return present.length;
    }
}
