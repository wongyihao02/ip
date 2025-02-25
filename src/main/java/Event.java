public class Event extends task{

    private final String when;
    private final String toWhen;

    public Event(String name, String when , String toWhen, boolean mark) {
        super(name, mark);
        this.when = when;
        this.toWhen = toWhen;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + when + " to: " + toWhen + ")";
    }
}
