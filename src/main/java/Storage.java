import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private final File file;

    public Storage(String filePath) {

        this.filePath = filePath;
//        this.file = new File(filePath);
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
    }

    public String[] readFile() throws FileNotFoundException {
        Scanner look = new Scanner(file);
        return look.nextLine().split(" ");
    }
}
