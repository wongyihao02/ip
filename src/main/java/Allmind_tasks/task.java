package Allmind_tasks;

public class task {

    private final String name;
    private boolean mark;

    public task(String name) {
        this.name = name;
        this.mark = false;
    }

    public boolean getMark() {
        return this.mark;
    }

    public boolean setMark(boolean mark) {
        return this.mark = mark;
    }

    @Override
    public String toString() {

        String temp;

        if (this.mark) {
            temp = "[X] ";
        } else {
            temp = "[ ] ";
        }
        return temp + name;
    }
}
