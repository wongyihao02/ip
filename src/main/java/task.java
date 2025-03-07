import java.time.format.DateTimeFormatter;


public class task {

    private final String name;
    private boolean mark;
    static protected final DateTimeFormatter inFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy kkmm");
    static protected final DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mm B");

    /**
     * instatiates a task object with the name and mark set to false
     *
     * @param name the name of the task
     */
    public task(String name) {
        this.name = name;
        this.mark = false;
    }

    /**
     * instantiates a task object with the name and the mark to be set to according to what is given
     * @param name the name of the task
     * @param mark the mark status that this task is set to
     */
    public task(String name, boolean mark) {
        this.name = name;
        this.mark = mark;
    }

    /**
     * gets the mark status of the task
     * @return the mark status
     */
    public boolean getMark() {
        return this.mark;
    }

    /**
     * sets the mark status of this task to what is given
     * @param mark the new mark status of the task
     */
    public void setMark(boolean mark) {
        this.mark = mark;
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
