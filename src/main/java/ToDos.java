public class ToDos extends task{

    public ToDos(String task, boolean mark) {
        super(task, mark);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
