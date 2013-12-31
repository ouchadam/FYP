public class Entry {

    private final EntryDelegate entryDelegate;

    public static void main(String... args) {
        Entry entry = Entry.newInstance();
        entry.instanceMain(args);
    }

    private static Entry newInstance() {
        return new Entry(new EntryDelegate());
    }

    public Entry(EntryDelegate entryDelegate) {
        this.entryDelegate = entryDelegate;
    }

    void instanceMain(String... args) {
        delegate(args);
    }

    private void delegate(String[] args) {
        // TODO handle args conversion if needed
        entryDelegate.delegate(args);
    }

}
