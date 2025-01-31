public class taskList {

    String[] listOfTasks;
    int numTasks;

    public taskList() {
        this.listOfTasks = new String[100];
        this.numTasks = 0;
    }

    void addTask(String task) {
        this.listOfTasks[numTasks] = task;
        System.out.println("added: " + task);
        numTasks++;
    }

    void list() {
        for(int i = 0; i < numTasks; i++) {
            System.out.println((i+1) + ". " + listOfTasks[i]);
        }
    }

    public void runTask(String task) {
        switch (task) {
            case "bye": break;
            case "list": list(); break;
            default: addTask(task); break;



        }
    }
}
