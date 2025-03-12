import java.time.LocalDateTime;

public class Event extends Task{

    private final LocalDateTime when;
    private final LocalDateTime toWhen;

    public Event(String name, String when , String toWhen, boolean mark) {
        super(name, mark);
        this.when = LocalDateTime.parse(when, inFormatter);
        this.toWhen = LocalDateTime.parse(toWhen, inFormatter);
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + when.format(outFormatter) + " to: " + toWhen.format(outFormatter) + ")";
    }
}
