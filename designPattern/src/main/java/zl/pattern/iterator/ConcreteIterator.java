package zl.pattern.iterator;

public class ConcreteIterator implements  Iterator {
    private ConcreteTeacher teacher;
    private int index = 0;
    private int size = 0;
    public ConcreteIterator(ConcreteTeacher teacher){
        this.teacher = teacher;
        size = teacher.getSize();
        index = 0;
    }

    @Override
    public void first() {
        index = 0;
    }

    @Override
    public void next() {
        if(index < size){
            index ++;
        }
    }

    @Override
    public boolean isDone() {
        return index >= size;
    }

    @Override
    public Object currentItems() {
        return teacher.getElement(index);
    }
}
