import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final File file;
    private final Ui ui;

    public Storage(String filePath) {


        if (!new File(filePath).exists()) {
            File dir = new File("data");
            dir.mkdir();
            File a = new File(filePath);
            try {
                a.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.file = a;
        } else {
            this.file = new File(filePath);
        }
        ui = new Ui();
    }

    /**
     * reads the data stored in the text file to get the tasks saved from the previous session.
     * @return arraylist where each element is the call to create a Task in Tasklist
     */
    public ArrayList<String> readFile() {
        ArrayList<String> list = new ArrayList<>();
        try {
            Scanner look = new Scanner(file);
            look.useDelimiter(System.lineSeparator());

            while (look.hasNext()) {
                list.add(look.next());
            }


        } catch (FileNotFoundException e) {
            ui.printMessage("File not found.");
        }
        return list;
    }

    /**
     * replaces the content of the text file with the given string.A line seperator is added at the end as well.
     * @param content The String to ovveride the textfile with.
     */
    public void writeReplaceFile(String content) {

        try {
            FileWriter writer = new FileWriter(file);
            writer.write(content + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            ui.printMessage(e.getMessage());
        }

    }

    /**
     * adds the given string at the end of the text file.A line seperator is added at the end as well.
     * @param content The String to be added at the end.
     */
    public void writeAppendFile(String content) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(content + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            ui.printMessage(e.getMessage());
        }
    }
}
