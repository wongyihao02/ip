public class taskList {

    String[] listOfTasks;
    boolean[] isMarked;
    int numTasks;


     public taskList() {    //creates a list that can store 100 tasks
        this.listOfTasks = new String[100];
        this.isMarked = new boolean[100];
        this.numTasks = 0;
    }

    void addTask(String task) {
        this.listOfTasks[numTasks] = task;
        System.out.println("added: " + task);
        numTasks++;
    }

    void list() {
        System.out.println("Complete list of tasks:");
        String temp;

        for(int i = 0; i < numTasks; i++) {
            if (isMarked[i]) {
                temp = ".[X] ";
            } else {
                temp = ".[ ] ";
            }
            System.out.println((i+1) + temp + listOfTasks[i]);
        }
    }

    void mark(int pos) {
        isMarked[pos - 1] = true;//have to consider bad input in future
    }

    void unmark(int pos) {
        isMarked[pos - 1] = false;
    }

    public void runTask(String task) {

        String[] words = task.split(" ");

        if (words[0].equals("mark")) {
            mark(Integer.parseInt(words[1]));
        } else if(words[0].equals("unmark")) {
            unmark(Integer.parseInt(words[1]));
        } else if(task.equals("list")) {
            list();
        } else {
            addTask(task);
        }

    }
}
