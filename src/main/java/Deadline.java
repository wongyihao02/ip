public class Deadline extends task {

    private final String byWhen;

    public Deadline(String name, String byWhen) {
        super(name);
        this.byWhen = byWhen;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + byWhen + ")";
    }
}
