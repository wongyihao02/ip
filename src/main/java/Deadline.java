import java.time.LocalDateTime;

public class Deadline extends task {

    private final LocalDateTime byWhen;

    public Deadline(String name, String byWhen, boolean mark) {
        super(name, mark);
        this.byWhen = LocalDateTime.parse(byWhen, inFormatter);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + byWhen.format(outFormatter) + ")";
    }
}
