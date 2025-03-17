

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


/**
 * It holds a list of tasks.Tasks can be added,removed and set as marked.
 * Can be searched for all tasks with the corresponding words.
 */
public class TaskList {


    private ArrayList<Task> listOfTasks;
    private final Storage storage;
    private final Ui ui;

    static int emptyResponseCount = 0;
    static int maxTolerance = 5;
    static final String[] emptyInputLines = new String[]{"no input detected",
            "please enter a valid input", "Is this intentional?"
            , "Invalid inputs are not appreciated"};


    /**
     * creates a new taskList object and loads the tasks from a text file if it exists and has tasks.
     * if it does not,a new file is created.
     *
     */
    public TaskList(Storage storage) {
        this.listOfTasks = new ArrayList<>();
        this.storage = storage;
        this.ui = new Ui();
        getFromList();
    }

    void getFromList() {
         ArrayList<String> list = storage.readFile();

            for (int i = 0; i < list.size(); i++) {
                addFromList(list.get(i));
            }
    }
    void addFromList(String line) {
        String[] input = line.split(" ");
        String line2 = String.join(" ", Arrays.copyOfRange(input, 1, input.length));

        if (input[0].equals("yes")) {

            switch (input[1]) {
                case "todo":
                    addTask(line2, validTasks.TODO, true, true);
                    break;
                case "deadline":
                    addTask(line2, validTasks.DEADLINE, true, true);
                    break;
                case "event":
                    addTask(line2, validTasks.EVENT, true, true);
                    break;

            }

        } else {
            switch (input[1]) {
                case "todo":
                    addTask(line2, validTasks.TODO, false, true);
                    break;
                case "deadline":
                    addTask(line2, validTasks.DEADLINE, false, true);
                    break;
                case "event":
                    addTask(line2, validTasks.EVENT, false, true);
                    break;

            }
        }
    }



    void addTask(String task, validTasks theTask, boolean mark, boolean inListAlr) {

        String[] words = task.split(" ");

        if (words.length == 1) {
            ui.printMessage("Invalid Input detected, just entering " + words[0]
                    + " alone is not allowed." + ".please enter a valid input");

        }

        switch (theTask) {
            case validTasks.TODO:
                String taskInput = "";
                for (int i = 1; i < words.length; i++) {
                    taskInput += words[i] + " ";
                }
                listOfTasks.add(new ToDos(taskInput.trim(), mark));

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
                        byWhen += words[i] + " ";
                    }

                }
                if (taskName.equals("") || byWhen.equals("")) {
                    ui.printMessage("incomplete command detected, please enter a complete command");
                    return;
                }


                listOfTasks.add(new Deadline(taskName.trim(), byWhen.trim(), mark));
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
                    } else if (words[i].equals("/to")) {
                        k += 1;
                        continue;
                    }

                    if (k == 1) {
                        taskName1 += words[i] + " ";
                    } else if (k == 2) {
                        fromWhen += words[i] + " ";
                    } else if (k == 3) {
                        toWhen += words[i] + " ";
                    }
                }

                if (taskName1.equals("") || fromWhen.equals("") || toWhen.equals("")) {
                    ui.printMessage("incomplete command detected, please enter a complete command");
                    return;
                }
                this.listOfTasks.add(new Event(taskName1.trim(), fromWhen.trim(), toWhen.trim(), mark));
                break;
            default:
                return;

        }

        if (!inListAlr) {
            saveTask(task);
        }

        ui.printMessage("added: " + listOfTasks.getLast().toString());

    }

    void list() {
        System.out.println("Complete list of tasks:");

        for (int i = 0; i < this.listOfTasks.size(); i++) {

            ui.printMessage((i + 1) + ". " + this.listOfTasks.get(i).toString());
        }
    }

    void mark(int pos) {
        this.listOfTasks.get(pos - 1).setMark(true);
        updateSavedTaskList(pos - 1, true);
    }

    void unmark(int pos) {
        this.listOfTasks.get(pos - 1).setMark(false);
        updateSavedTaskList(pos - 1, false);

    }

    void emptyInputResponse() {
        if (emptyResponseCount > maxTolerance) {
            ui.printMessage("hmmm");
        } else {
            ui.printMessage(emptyInputLines[new Random().nextInt(emptyInputLines.length)]);
            emptyResponseCount++;
        }
    }


    void delete(int pos) {
        ui.printMessage("Deleting task: " + this.listOfTasks.get(pos - 1).toString());
        this.listOfTasks.remove(pos - 1);
        removeTaskFromSavedList(pos - 1);
    }

    /**
     * takes in the Task and adds it to the textfile that stores the taskList
     * @param task String of the task to be saved
     */
    public void saveTask(String task) {
        storage.writeAppendFile("no " + task);
    }

    /**
     * This updates the mark status of a task in the taskList.
     * @param pos the position of the task to be updated
     * @param mark the markstatus to be changed to
     */
    public void updateSavedTaskList(int pos, boolean mark) {
        ArrayList<String> list = storage.readFile();
        String placeHolder = list.get(pos);

        if (mark) {
            placeHolder = placeHolder.replaceFirst("no " , "yes ");
        } else {
            placeHolder = placeHolder.replaceFirst("yes " , "no ");
        }
        list.set(pos, placeHolder);
        storage.writeReplaceFile(String.join(System.lineSeparator(), list));

    }

    /**
     * removes the task from the saved task text file
     * @param pos the line in which the task to be deleted is im
     */
    public void removeTaskFromSavedList(int pos) {
        ArrayList<String> list = storage.readFile();
        list.remove(pos);
        storage.writeReplaceFile(String.join(System.lineSeparator(), list));

    }
    
    public void find (String task) {
        int i = 1;

        for (int j = 0; j < listOfTasks.size(); j++) {
            if (listOfTasks.get(j).toString().contains(task)) {
                ui.printMessage(i + ". " + listOfTasks.get(j).toString());
                i++;
            }
        }
    }


    /**
     * Takes in a String that represents a command and carries out the command if it is a valid function of the class taskList.
     * <p>
     *     This is a new paragraph.
     * </p>
     * @param task A String that possibly represents a command,is a user input.
     * @return nothing.
     */
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
                addTask(task, validTasks.TODO, false, false);
                break;
            case "deadline":
                addTask(task, validTasks.DEADLINE, false, false);
                break;
            case "event":
                addTask(task, validTasks.EVENT, false, false);
                break;
            case "delete":
                delete(Integer.parseInt(words[1]));
                break;
            case "find":
                find(task.replaceFirst("find", " ").trim());
                break;
            default:
                ui.printMessage("unknown task detected: " + task);
        }
    }

}


