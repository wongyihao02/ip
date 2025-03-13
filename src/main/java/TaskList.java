
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * It holds a list of tasks.Tasks can be added,removed and set as marked.
 * Can be searched for all tasks with the corresponding words.
 */
public class TaskList {


    private ArrayList<Task> listOfTasks;
    private String filePath;

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
    public TaskList(String filePath) {
        this.listOfTasks = new ArrayList<>();
        this.filePath = filePath;
        //this.isMarked = new boolean[100];

        if (!new File(filePath).exists()) {
            File dir = new File("data");
            dir.mkdir();
            File a = new File(filePath);
            try {
                a.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            getFromList();
        }
    }

    void getFromList() {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            scanner.useDelimiter(System.lineSeparator());
            String line;
            //String[] input;


            while (scanner.hasNext()) {
                line = scanner.next();
                addFromList(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    void addFromList(String line) {
        String[] input = line.split(" ");
        String line2 = String.join(" ", Arrays.copyOfRange(input, 1, input.length));

        if (input[0].equals("yes")) {

            switch (input[1]) {
                case "todo":
                    addTask(line, validTasks.TODO, true, true);
                    break;
                case "deadline":
                    addTask(line, validTasks.DEADLINE, true, true);
                    break;
                case "event":
                    addTask(line, validTasks.EVENT, true, true);
                    break;

            }

        } else {
            switch (input[1]) {
                case "todo":
                    addTask(line, validTasks.TODO, false, true);
                    break;
                case "deadline":
                    addTask(line, validTasks.DEADLINE, false, true);
                    break;
                case "event":
                    addTask(line, validTasks.EVENT, false, true);
                    break;

            }
        }
    }

//        switch (theTask) {
//            case validTasks.TODO:
//                String taskInput = "";
//                for (int i = 1; i < words.length; i++) {
//                    taskInput += words[i] + " ";
//                }
//                this.listOfTasks.add(new ToDos(taskInput.trim()));

    void addTask(String task, validTasks theTask, boolean mark, boolean inListAlr) {

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
                    System.out.println("incomplete command detected, please enter a complete command");
                    break;
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
                this.listOfTasks.add(new Event(taskName1.trim(), fromWhen.trim(), toWhen.trim(), mark));
                break;
            default:
                return;

        }

        if (!inListAlr) {
            saveTask(task);
        }

        System.out.println("added: " + listOfTasks.getLast().toString());

    }

    void list() {
        System.out.println("Complete list of tasks:");

        for (int i = 0; i < this.listOfTasks.size(); i++) {

            System.out.println((i + 1) + ". " + this.listOfTasks.get(i).toString());
        }
    }

    void mark(int pos) {
        this.listOfTasks.get(pos - 1).setMark(true);//have to consider bad input in future
        updateSavedTaskList(pos, true);
    }

    void unmark(int pos) {
        this.listOfTasks.get(pos - 1).setMark(false);
        updateSavedTaskList(pos, false);

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
        removeTaskFromSavedList(pos);
    }

    /**
     * takes in the Task and adds it to the textfile that stores the taskList
     * @param task String of the task to be saved
     */
    public void saveTask(String task) {

        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write("no " + task + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage() + " detected");
        }
    }

    /**
     * This updates the mark status of a task in the taskList.
     * @param pos the position of the task to be updated
     * @param mark the markstatus to be changed to
     */
    public void updateSavedTaskList(int pos, boolean mark) {
        try {
            File a = new File(filePath);
            Scanner look = new Scanner(a);
            look.useDelimiter(System.lineSeparator());
            String final_out = "";
            String looked = "";
            String intermediate;

            for (int i = 0; i < listOfTasks.size(); i++) {

                if (look.hasNext()) {
                    looked = look.next();
                }

                if (i + 1 == pos) {
                    if (mark) {
                        final_out += looked.replaceFirst("no ", "yes ") + System.lineSeparator();
                    } else {
                        final_out += looked.replaceFirst("yes ", "no ") + System.lineSeparator();
                    }
                } else {

                    final_out += looked + System.lineSeparator();
                }

            }

            FileWriter hsy = new FileWriter(filePath);
            hsy.write(final_out);
            hsy.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage() + " detected");
        } catch (IOException e) {
            System.out.println(e.getMessage() + " detected");
        }

    }

    /**
     * removes the task from the saved task text file
     * @param pos the line in which the task to be deleted is im
     */
    public void removeTaskFromSavedList(int pos) {
        try {
            File a = new File(filePath);
            Scanner look = new Scanner(a);
            look.useDelimiter(System.lineSeparator());
            String final_out = "";
            String looked = "";


            for (int i = 0; i < listOfTasks.size(); i++) {
                if (look.hasNext()) {
                    looked = look.next();
                }
                if (i + 1 != pos) {
                    final_out += looked + System.lineSeparator();
                }

                }

            FileWriter hsy = new FileWriter(filePath);
            hsy.write(final_out);
            hsy.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage() + " detected");
        } catch (IOException e) {
            System.out.println(e.getMessage() + " detected");
        }
    }
    
    public void find (String task) {
        int i = 1;

        for (int j = 0; j < listOfTasks.size(); j++) {
            if (listOfTasks.get(j).toString().contains(task)) {
                System.out.println(i + ". " + listOfTasks.get(j).toString());
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
                System.out.println("unknown task detected: " + task);
        }

//
    }

}


