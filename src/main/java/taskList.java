
import java.io.*;
import java.nio.file.Path;
import java.util.Random;
import java.util.Scanner;


public class taskList {

    task[] listOfTasks;
    //boolean[] isMarked;
    int numTasks;
    static String filePath = "data/savedTaskList.txt";
    static int emptyResponseCount = 0;
    static int maxTolerance = 5;
    static String[] emptyInputLines = new String[]{"no input detected",
            "please enter a valid input", "Is this intentional?"
            , "Invalid inputs are not appreciated"};

    public taskList() {    //creates a list that can store 100 tasks
        this.listOfTasks = new task[100];
        //this.isMarked = new boolean[100];
        this.numTasks = 0;
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
            String[] input;


            while (scanner.hasNext()) {

                line = scanner.next();
                input = line.split(" ");

                if (input[0].equals("yes")) {
                    switch (input[1]) {
                        case "todo":
                            addTask(line.replaceFirst("yes ", ""), validTasks.TODO, true, true);
                            break;
                        case "deadline":
                            addTask(line.replaceFirst("yes ", ""), validTasks.DEADLINE, true, true);
                            break;
                        case "event":
                            addTask(line.replaceFirst("yes ", ""), validTasks.EVENT, true, true);
                            break;

                    }

                } else {
                    switch (input[1]) {
                        case "todo":
                            addTask(line.replaceFirst("yes ", ""), validTasks.TODO, false, true);
                            break;
                        case "deadline":
                            addTask(line.replaceFirst("yes ", ""), validTasks.DEADLINE, false, true);
                            break;
                        case "event":
                            addTask(line.replaceFirst("yes ", ""), validTasks.EVENT, false, true);
                            break;

                    }
                }


            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    void addTask(String task, validTasks theTask, boolean mark, boolean inListAlr){

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
                    listOfTasks[numTasks] = new ToDos(taskInput.trim(), mark);

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


                    listOfTasks[numTasks] = new Deadline(taskName.trim(), byWhen.trim(), mark);
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
                        } else {
                            toWhen += words[i] + " ";
                        }
                    }

                    if (taskName1.equals("") || fromWhen.equals("") || toWhen.equals("")) {
                        System.out.println("incomplete command detected, please enter a complete command");
                        break;
                    }
                    listOfTasks[numTasks] = new Event(taskName1.trim(), fromWhen.trim(), toWhen.trim(), mark);
                    break;
                default:
                    return;
            }
            if (!inListAlr) {
                saveTask(task);
            }

            System.out.println("added: " + task);
            numTasks++;

    }

    void list() {
        System.out.println("Complete list of tasks:");

        for (int i = 0; i < numTasks; i++) {

            System.out.println((i + 1) + ". " + listOfTasks[i].toString());
        }
    }

    void mark(int pos) {
        this.listOfTasks[pos - 1].setMark(true);//have to consider bad input in future
        updateSavedTaskList(pos, true);
    }

    void unmark(int pos) {
        this.listOfTasks[pos - 1].setMark(false);
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

    public void saveTask(String task) {

        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write("no " + task + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage() + " detected");
        }
    }

    public void updateSavedTaskList(int pos, boolean mark) {
        try {
            File a = new File(filePath);
            Scanner look = new Scanner(a);
            look.useDelimiter(System.lineSeparator());
            String final_out = "";
            String looked = "";
            String intermediate;

            for (int i = 0; i < numTasks; i++) {

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


