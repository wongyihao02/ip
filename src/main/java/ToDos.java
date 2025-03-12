public class ToDos extends Task{

    public ToDos(String task, boolean mark) {
        super(task, mark);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
