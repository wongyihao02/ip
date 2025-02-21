
import java.util.ArrayList;
import java.util.Random;

public class taskList {

    ArrayList<task> listOfTasks;
    static int emptyResponseCount = 0;
    static int maxTolerance = 5;
    static String[] emptyInputLines = new String[]{"no input detected",
            "please enter a valid input", "Is this intentional?"
            , "Invalid inputs are not appreciated"};

    public taskList() {    //creates a list that can store 100 tasks
        this.listOfTasks = new ArrayList<>();
        //this.isMarked = new boolean[100];
    }

    void addTask(String task, validTasks theTask) {

        String[] words = task.split(" ");

        if (words.length == 1) {
            System.out.println("Invalid Input detected, just entering " + words[0]
                    + " alone is not allowed." + ".please enter a valid input");
        }

        switch (theTask) {
            case validTasks.TODO:
                String taskInput = "";
                for (int i = 1; i < words.length; i++) {
                    taskInput += words[i] + " ";
                }
                this.listOfTasks.add(new ToDos(taskInput.trim()));

                break;
            case validTasks.DEADLINE:
                boolean p = true;
                String taskName = "";
                String byWhen = "";

                for (int i = 1; i < words.length; i++) {
                    if (words[i].equals("/by")) {
                        p = false;
                        continue;
                    }

                    if (p) {
                        taskName += words[i] + " ";
                    } else {
                        byWhen +=words[i] + " ";
                    }
                }


                if (taskName.equals("") || byWhen.equals("")) {
                    System.out.println("incomplete command detected, please enter a complete command");
                    break;
                }


                this.listOfTasks.add(new Deadline(taskName.trim(), byWhen.trim()));
                break;
            case validTasks.EVENT:
                int k = 1;
                String taskName1 = "";
                String fromWhen = "";
                String toWhen = "";

                for (int i = 1; i < words.length; i++) {
                    if (words[i].equals("/from")) {
                        k += 1;
                        continue;
                    } else if(words[i].equals("/to")) {
                        k += 1;
                        continue;
                    }

                    if (k == 1) {
                        taskName1 += words[i] + " ";
                    } else if (k == 2) {
                        fromWhen += words[i] + " ";
                    } else {
                        toWhen += words[i] + " ";
                    }
                }

                if (taskName1.equals("") || fromWhen.equals("") || toWhen.equals("")) {
                    System.out.println("incomplete command detected, please enter a complete command");
                    break;
                }
                this.listOfTasks.add(new Event(taskName1.trim(), fromWhen.trim(), toWhen.trim()));
                break;
            default:
                return;
        }
        System.out.println("added: " + task);

    }

    void list() {
        System.out.println("Complete list of tasks:");

        for (int i = 0; i < this.listOfTasks.size(); i++) {

            System.out.println((i + 1) + ". " + this.listOfTasks.get(i).toString());
        }
    }

    void mark(int pos) {
        this.listOfTasks.get(pos - 1).setMark(true);//have to consider bad input in future
    }

    void unmark(int pos) {
        this.listOfTasks.get(pos - 1).setMark(false);
    }

    void emptyInputResponse() {
        if (emptyResponseCount > maxTolerance) {
            System.out.println("hmmm");
        } else {
            System.out.println(emptyInputLines[new Random().nextInt(emptyInputLines.length)]);
            emptyResponseCount++;
        }
    }

    void delete(int pos) {
        System.out.println("Deleting task: " + this.listOfTasks.get(pos - 1).toString());
        this.listOfTasks.remove(pos - 1);

    }

    public void runTask(String task) {

        if (task.isBlank()) {
            emptyInputResponse();
            return;
        }

        String[] words = task.split(" ");

        switch (words[0]) {
            case "mark":
                mark(Integer.parseInt(words[1]));
                break;
            case "unmark":
                unmark(Integer.parseInt(words[1]));
                break;
            case "list":
                list();
                break;
            case "todo":
                addTask(task, validTasks.TODO);
                break;
            case "deadline":
                addTask(task, validTasks.DEADLINE);
                break;
            case "event":
                addTask(task, validTasks.EVENT);
                break;
            case "delete":
                delete(Integer.parseInt(words[1]));
                break;
            default:
                System.out.println("unknown task detected: " + task);
        }

//        if (words[0].equals("mark")) {
//            mark(Integer.parseInt(words[1]));
//        } else if(words[0].equals("unmark")) {
//            unmark(Integer.parseInt(words[1]));
//        } else if(task.equals("list")) {
//            list();
//        } else {
//            addTask(task);
    }

}


